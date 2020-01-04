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


public class SignInRequest {
    /**
     * alias
     */
    @com.google.gson.annotations.SerializedName("alias")
    private String alias = null;
    /**
     * password
     */
    @com.google.gson.annotations.SerializedName("password")
    private String password = null;

    /**
     * alias
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
     * password
     *
     * @return password
     **/
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of password.
     *
     * @param password the new value
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
