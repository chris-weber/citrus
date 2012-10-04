/*
 * Copyright 2006-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.actions;

import org.springframework.beans.factory.annotation.Autowired;

import com.consol.citrus.TestAction;
import com.consol.citrus.TestActor;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.report.TestActionListeners;
import com.consol.citrus.util.TestActionExecutionLogger;

/**
 * Abstract base class for test acions. Class provides a default name and description.
 * @author Christoph Deppisch
 */
public abstract class AbstractTestAction implements TestAction {

    /** Describing the test action */
    protected String description;

    /** TestAction name injected as spring bean name */
    private String name = this.getClass().getSimpleName();
    
    /** This actions explicit test actor */
    private TestActor actor;
    
    @Autowired
    private TestActionListeners testActionListeners = new TestActionListeners();
    
    /**
     * Do basic logging and delegate execution to subclass.
     */
    public void execute(TestContext context) {
        if (!isDisabled(context)) {
            TestActionExecutionLogger.logTestAction(this);
            testActionListeners.onTestActionStart(this);
            
            doExecute(context);
            
            testActionListeners.onTestActionSuccess(this);
        } else {
            TestActionExecutionLogger.logDisabledTestAction(this);
            testActionListeners.onTestActionSkipped(this);
        }
    }
    
    /**
     * Checks if this test action is disabled. Delegates to test actor defined
     * for this test action by default. Subclasses may add additional disabled logic here.
     * 
     * @param context the current test context.
     * @return
     */
    public boolean isDisabled(TestContext context) {
        if (actor != null) {
            return actor.isDisabled();
        } else {
            return false;
        }
    }

    /**
     * Subclasses may add custom execution logic here.
     */
    public abstract void doExecute(TestContext context);

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * (non-Javadoc)
     * @see com.consol.citrus.TestAction#setName(java.lang.String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the actor.
     * @return the actor the actor to get.
     */
    public TestActor getActor() {
        return actor;
    }

    /**
     * Sets the actor.
     * @param actor the actor to set
     */
    public void setActor(TestActor actor) {
        this.actor = actor;
    }
}
