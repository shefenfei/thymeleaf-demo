package com.fisher.arch.webflux.demo.service;

import com.fisher.arch.webflux.demo.model.SceneRule;
import org.kie.internal.KnowledgeBase;

public interface DroolsService {

    String filterRules();


    String filterRules1();


    KnowledgeBase buildKnowLedge(String ruleName, String rule);


    void checkRule(SceneRule sceneRule);

    String sayFromService();

    void executeRules(SceneRule sceneRule);
}
