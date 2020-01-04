/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.twitterclone.api.model;


public class StatusRequest {
    /**
     * id of status
     */
    @com.google.gson.annotations.SerializedName("alias")
    private String alias = null;
    @com.google.gson.annotations.SerializedName("time")
    private String time = null;

    /**
     * id of status
     *
     * @return alias
     **/
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of alias.
     *
     * @param alias the new value
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets time
     *
     * @return time
     **/
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of time.
     *
     * @param time the new value
     */
    public void setTime(String time) {
        this.time = time;
    }

}
