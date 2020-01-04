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


public class RegisterRequest {
    /**
     * name
     */
    @com.google.gson.annotations.SerializedName("firstName")
    private String firstName = null;
    /**
     * name
     */
    @com.google.gson.annotations.SerializedName("lastName")
    private String lastName = null;
    /**
     * password
     */
    @com.google.gson.annotations.SerializedName("password")
    private String password = null;
    /**
     * alias
     */
    @com.google.gson.annotations.SerializedName("alias")
    private String alias = null;
    /**
     * url
     */
    @com.google.gson.annotations.SerializedName("url")
    private String url = null;

    /**
     * name
     *
     * @return firstName
     **/
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of firstName.
     *
     * @param firstName the new value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * name
     *
     * @return lastName
     **/
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of lastName.
     *
     * @param lastName the new value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * url
     *
     * @return url
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of url.
     *
     * @param url the new value
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
