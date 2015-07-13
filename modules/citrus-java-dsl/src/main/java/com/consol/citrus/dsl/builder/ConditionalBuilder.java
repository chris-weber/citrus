/*
 * Copyright 2006-2015 the original author or authors.
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

package com.consol.citrus.dsl.builder;

import com.consol.citrus.container.Conditional;

/**
 * Action builder creates a conditional container, which executes nested test actions
 * if condition expression evaluates to true.
 * 
 * @author Christoph Deppisch
 * @since 2.2.1
 */
public class ConditionalBuilder extends AbstractTestActionContainerBuilder<Conditional> {

	/**
	 * Constructor using action field.
	 * @param action
	 */
	public ConditionalBuilder(Conditional action) {
	    super(action);
    }

	/**
	 * Default constructor.
	 */
	public ConditionalBuilder() {
		super(new Conditional());
	}

	/**
     * Condition which allows execution if true.
     * @param expression
     */
	public ConditionalBuilder when(String expression) {
		action.setExpression(expression);
		return this;
	}
}
