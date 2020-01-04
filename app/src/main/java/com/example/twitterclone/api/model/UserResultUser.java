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


public class UserResultUser {
    /**
     * first name
     */
    @com.google.gson.annotations.SerializedName("firstName")
    private String firstName = null;
    /**
     * last name
     */
    @com.google.gson.annotations.SerializedName("lastName")
    private String lastName = null;
    /**
     * alias
     */
    @com.google.gson.annotations.SerializedName("alias")
    private String alias = null;
    /**
     * profile pic
     */
    @com.google.gson.annotations.SerializedName("urlPicture")
    private String urlPicture = null;

    /**
     * first name
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
     * last name
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
     * profile pic
     *
     * @return urlPicture
     **/
    public String getUrlPicture() {
        return urlPicture;
    }

    /**
     * Sets the value of urlPicture.
     *
     * @param urlPicture the new value
     */
    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

}
