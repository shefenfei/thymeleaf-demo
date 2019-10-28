package com.fisher.arch.webflux.demo.controller;

import com.fisher.arch.webflux.demo.model.Item;
import com.fisher.arch.webflux.demo.model.SceneRule;
import com.fisher.arch.webflux.demo.service.DemoService;
import com.fisher.arch.webflux.demo.service.DroolsService;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.Future;

@RequestMapping("/rest")
@RestController
public class TestController {

    private DemoService demoService;
    private DroolsService droolsService;

    private Future<String> futureResult;

    public TestController(DemoService demoService, DroolsService droolsService) {
        this.demoService = demoService;
        this.droolsService = droolsService;
    }

    @GetMapping("/findOne")
    public Mono<String> findOne() {
        demoService.printMessage();
        return Mono.just("hello from webflux");
    }


    @GetMapping("/getResult")
    public Mono<String> getResult() {
        futureResult = demoService.asyncMethodWithReturnType();
        return Mono.just("hello from webflux");
    }


    @GetMapping("/getResult1")
    public Mono<Boolean> getResult1() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/first.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());

        StatefulKnowledgeSession kieSession = knowledgeBase.newStatefulKnowledgeSession();

        Item item = new Item();
        item.setId(1);
        item.setPrice(100);
        item.setName("book");

        kieSession.insert(item);
        int i = kieSession.fireAllRules();

        System.out.println("i" + i);
        return Mono.just(true);
    }


    @GetMapping("/drools")
    public Mono<String> testGetFromDrools() {
        String s = droolsService.filterRules1();
        return Mono.just(s);
    }



    @GetMapping("/execute")
    public Mono<String> executeRule() {
        SceneRule sceneRule = new SceneRule();
        sceneRule.setSceneId(1);
        sceneRule.setPlazaId(0);
        sceneRule.setActivityTime("2019-10-28 11:11:11");
        droolsService.executeRules(sceneRule);
        return Mono.just("hahh");
    }




    @PostMapping("dynamic")
    public Mono<String> testDynamicRule(@RequestBody SceneRule sceneRule) {
        droolsService.checkRule(sceneRule);
        return Mono.just("hi");
    }

}