package com.fisher.arch.webflux.demo.service;

import com.fisher.arch.webflux.demo.model.Item;
import com.fisher.arch.webflux.demo.model.Message;
import com.fisher.arch.webflux.demo.model.SceneRule;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DroolsServiceImpl implements DroolsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsService.class);

    private StatefulKnowledgeSession knowledgeSession;
    private KieSession kieSession;

    public DroolsServiceImpl(StatefulKnowledgeSession knowledgeSession, KieSession kieSession) {
        this.knowledgeSession = knowledgeSession;
        this.kieSession = kieSession;
    }

    @Override
    public String filterRules() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer classpathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = classpathContainer.newKieSession("ksession-rules");

        Item item = new Item();
        item.setId(1);
        item.setPrice(100);
        item.setName("book");

        kieSession.insert(item);
        kieSession.fireAllRules();
        kieSession.dispose();

        return item.getName();
    }


    @Override
    public String filterRules1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer classpathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = classpathContainer.newKieSession("ksession-rules");

        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kieSession.insert(message);//插入
        kieSession.fireAllRules();//执行规则
        kieSession.dispose();

        return message.getMessage();
    }

    /**
     * 动态规则使用
     * @param ruleName
     * @param rule
     * @return
     */
    @Override
    public KnowledgeBase buildKnowLedge(String ruleName, String rule) {
        //1, 读取 db rules

        //2, 拼接rules

        //3, 执行规则
        String str = "";
        str += "package org.kie \n";
        str += "import " + Message.class.getCanonicalName() + "\n";
        str += "import " + Item.class.getCanonicalName() + "\n";
        str += "import " + SceneRule.class.getCanonicalName() + "\n";
        str += "global java.util.List list \n";

        int i = 0;
        str += "rule " + ruleName + "  when \n";
        str += rule;
        str += "then \n";
        str += "    System.out.println(scene.getSceneId());\n";
        str += "end \n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        kbuilder.add(ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL);
        KieBaseConfiguration kconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kconf);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    @Override
    public void checkRule(SceneRule sceneRule) {
        knowledgeSession.insert(sceneRule);
        knowledgeSession.fireAllRules();
    }

    @Override
    public String sayFromService() {
        System.out.println("hello from other app");
        return "";
    }

    @Override
    public void executeRules(SceneRule sceneRule) {
        kieSession.insert(sceneRule);
        kieSession.fireAllRules();
        kieSession.dispose();
        LOGGER.info("规则引擎执行完成....");
    }


    private List<String> rules() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i =0; i< 5; i++) {
            SceneRule sceneRule = new SceneRule();
            sceneRule.setPlazaId(i);
            sceneRule.setSceneId(i + 1);
            sceneRule.setActivityTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            String rule = "scene: SceneRule( scene.getSceneId() == "+ sceneRule.getSceneId() + ")";
            strings.add(rule);
        }
        return strings;
    }


    public List<KnowledgeBase> knowledgeBases() {
        List<KnowledgeBase> knowledgeBases = new ArrayList<>();
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        rules().forEach(rule -> {
            KnowledgeBase knowledgeBase = buildKnowLedge("rule1", rule);
            knowledgeBases.add(knowledgeBase);
        });

        return knowledgeBases;
    }

    public void test() {
//        KieServices kieServices = KieServices.Factory.get();


    }

    public static void main(String[] args) {
        Date date = new Date();
        long time = date.getTime();
        String str = "\"" + time +"\"";
        System.out.println(str);


        SceneRule sceneRule = new SceneRule();
        sceneRule.setPlazaId(1);
        sceneRule.setSceneId(1 + 1);
        sceneRule.setActivityTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String s1 = "date-effective " + "\"" + sceneRule.getActivityTime() + "\"";
        System.out.println(s1);

    }

}
