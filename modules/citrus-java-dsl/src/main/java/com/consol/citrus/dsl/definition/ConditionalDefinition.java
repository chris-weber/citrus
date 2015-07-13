/*
 * Copyright 2006-2012 the original author or authors.
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

package com.consol.citrus.dsl.definition;

import com.consol.citrus.container.Conditional;

/**
 * Action definition creates a conditional container, which executes nested test actions 
 * if condition expression evaluates to true.
 * 
 * @author Max Argyo, Giulia DelBravo
 * @since 1.3
 * @deprecated since 2.2.1 in favor of using {@link com.consol.citrus.dsl.builder.ConditionalBuilder}
 */
public class ConditionalDefinition extends AbstractActionContainerDefinition<Conditional> {

	/**
	 * Constructor using action field.
	 * @param action
	 */
	public ConditionalDefinition(Conditional action) {
	    super(action);
    }

	/**
	 * Default constructor.
	 */
	public ConditionalDefinition() {
		super(new Conditional());
	}

	/**
     * Condition which allows execution if true.
     * @param expression
     */
	public ConditionalDefinition when(String expression) {
		action.setExpression(expression);
		return this;
	}
}
