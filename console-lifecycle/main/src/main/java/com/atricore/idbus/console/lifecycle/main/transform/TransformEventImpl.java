/*
 * Copyright (c) 2010., Atricore Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.atricore.idbus.console.lifecycle.main.transform;

/**
 * @author <a href="mailto:sgonzalez@atricore.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id$
 */
public class TransformEventImpl implements TransformEvent {

    private IdApplianceTransformationContext context;

    private Object data;

    private Object[] lastResults;

    public TransformEventImpl(IdApplianceTransformationContext context, Object data, Object[] lastResults) {
        this.context = context;
        this.data = data;
        this.lastResults = lastResults;
    }

    public IdApplianceTransformationContext getContext() {
        return context;
    }

    public Object getData() {
        return data;
    }


    /**
     * Each element in the object array contains another object array with results of each child.
     */

    public Object[] getLastResults() {
        return lastResults;
    }
}