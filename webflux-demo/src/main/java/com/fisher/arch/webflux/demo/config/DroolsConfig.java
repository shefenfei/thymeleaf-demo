package com.fisher.arch.webflux.demo.config;

import com.fisher.arch.webflux.demo.model.Item;
import com.fisher.arch.webflux.demo.model.Message;
import com.fisher.arch.webflux.demo.model.SceneRule;
import com.fisher.arch.webflux.demo.service.DroolsService;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Drools规则引擎配置
 */
@Configuration
public class DroolsConfig {

    @Bean
    public KieSession kieSession() {
        KieHelper kieHelper = new KieHelper();

        //加载需要用的规则
        for (int j =0; j< 5; j++) {
            SceneRule sceneRule = new SceneRule();
            sceneRule.setPlazaId(j);
            sceneRule.setSceneId(j + 1);
            sceneRule.setActivityTime(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));


            //规则引擎要求日期格式  dd-MMM-yyyy
            String rule = "scene: SceneRule( sceneId == " + sceneRule.getSceneId() + ", plazaId == " + sceneRule.getPlazaId() + ")";

            String str = "";
            str += "package org.kie \n";
            str += "import " + Message.class.getCanonicalName() + "\n";
            str += "import " + Item.class.getCanonicalName() + "\n";
            str += "import " + SceneRule.class.getCanonicalName() + "\n";
            str += "import " + DroolsService.class.getCanonicalName() + "\n";
            str += "global java.util.List list\n";

            String s = "a";
            int i = 0;
            str += "rule " + "rule" + j + "\n";
            str += "date-effective " + "\"" + sceneRule.getActivityTime() + "\"";
            str += "when \n";
            str += rule;
            str += "then \n";
            str += "    System.out.println(scene.getSceneId());\n";
            str += "end \n";

            kieHelper.addContent(str, ResourceType.DRL);
        }
        KieSession kieSession = kieHelper.build().newKieSession();
        return kieSession;
    }


    @Bean
    public KnowledgeBuilder knowledgeBuilderFactory() {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        //加载需要用的规则
        for (int j =0; j< 5; j++) {
            SceneRule sceneRule = new SceneRule();
            sceneRule.setPlazaId(j);
            sceneRule.setSceneId(j + 1);
            sceneRule.setActivityTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            String rule = "scene: SceneRule( scene.getSceneId() == "+ sceneRule.getSceneId() + ")";

            String str = "";
            str += "package org.kie \n";
            str += "import " + Message.class.getCanonicalName() + "\n";
            str += "import " + Item.class.getCanonicalName() + "\n";
            str += "import " + SceneRule.class.getCanonicalName() + "\n";
            str += "import " + DroolsService.class.getCanonicalName() + "\n";
            str += "global java.util.List list\n";

            int i = 0;
            str += "rule " + "rule"+j + "  when \n";
            str += rule;
            str += "then \n";
            str += "    System.out.println(scene.getSceneId());\n";
            str += "end \n";

            kbuilder.add(ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL);
        }

        kbuilder.getErrors().forEach(error -> {
            System.out.println(error);
        });

        return kbuilder;
    }


    @Bean
    public StatefulKnowledgeSession knowledgeSession(KnowledgeBuilder knowledgeBuilder) {
        KieBaseConfiguration kconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kconf);
        kbase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        StatefulKnowledgeSession knowledgeSession = kbase.newStatefulKnowledgeSession();
        return knowledgeSession;
    }


}
