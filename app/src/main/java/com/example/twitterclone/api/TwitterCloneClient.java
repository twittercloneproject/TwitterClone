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

package com.example.twitterclone.api;

import java.util.*;

import com.example.twitterclone.api.model.FollowResult;
import com.example.twitterclone.api.model.FollowRequest;
import com.example.twitterclone.api.model.Empty;
import com.example.twitterclone.api.model.StatusesResult;
import com.example.twitterclone.api.model.RequestByAlias;
import com.example.twitterclone.api.model.UsersResult;
import com.example.twitterclone.api.model.HashtagRequest;
import com.example.twitterclone.api.model.StatusRequest;
import com.example.twitterclone.api.model.StatusResult;
import com.example.twitterclone.api.model.UserResult;
import com.example.twitterclone.api.model.UserRequest;
import com.example.twitterclone.api.model.IsFollowingResult;
import com.example.twitterclone.api.model.IsFollowingRequest;
import com.example.twitterclone.api.model.RegisterRequest;
import com.example.twitterclone.api.model.RegisterResult;
import com.example.twitterclone.api.model.SendStatusResult;
import com.example.twitterclone.api.model.SendStatusRequest;
import com.example.twitterclone.api.model.SignInRequest;
import com.example.twitterclone.api.model.SignInResult;
import com.example.twitterclone.api.model.UnFollowResult;
import com.example.twitterclone.api.model.UnFollowRequest;


@com.amazonaws.mobileconnectors.apigateway.annotation.Service(endpoint = "https://7bz7j43uff.execute-api.us-west-2.amazonaws.com/twitterclone")
public interface TwitterCloneClient {


    /**
     * A generic invoker to invoke any API Gateway endpoint.
     * @param request
     * @return ApiResponse
     */
    com.amazonaws.mobileconnectors.apigateway.ApiResponse execute(com.amazonaws.mobileconnectors.apigateway.ApiRequest request);
    
    /**
     * 
     * 
     * @param body 
     * @return FollowResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/followuser", method = "POST")
    FollowResult followuserPost(
            FollowRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/followuser", method = "OPTIONS")
    Empty followuserOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return StatusesResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfeed", method = "POST")
    StatusesResult getfeedPost(
            RequestByAlias body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfeed", method = "OPTIONS")
    Empty getfeedOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return UsersResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfollowers", method = "POST")
    UsersResult getfollowersPost(
            RequestByAlias body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfollowers", method = "OPTIONS")
    Empty getfollowersOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return UsersResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfollowing", method = "POST")
    UsersResult getfollowingPost(
            RequestByAlias body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getfollowing", method = "OPTIONS")
    Empty getfollowingOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return StatusesResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/gethashtags", method = "POST")
    StatusesResult gethashtagsPost(
            HashtagRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/gethashtags", method = "OPTIONS")
    Empty gethashtagsOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return StatusResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getstatus", method = "POST")
    StatusResult getstatusPost(
            StatusRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getstatus", method = "OPTIONS")
    Empty getstatusOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return StatusesResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getstory", method = "POST")
    StatusesResult getstoryPost(
            RequestByAlias body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getstory", method = "OPTIONS")
    Empty getstoryOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return UserResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getuser", method = "POST")
    UserResult getuserPost(
            RequestByAlias body);
    
    /**
     * 
     * 
     * @param body 
     * @return UserResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/getuser", method = "OPTIONS")
    UserResult getuserOptions(
            UserRequest body);
    
    /**
     * 
     * 
     * @param body 
     * @return IsFollowingResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/isfollowing", method = "POST")
    IsFollowingResult isfollowingPost(
            IsFollowingRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/isfollowing", method = "OPTIONS")
    Empty isfollowingOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return RegisterResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/register", method = "POST")
    RegisterResult registerPost(
            RegisterRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/register", method = "OPTIONS")
    Empty registerOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return SendStatusResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/sendstatus", method = "POST")
    SendStatusResult sendstatusPost(
            SendStatusRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/sendstatus", method = "OPTIONS")
    Empty sendstatusOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return SignInResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/signin", method = "POST")
    SignInResult signinPost(
            SignInRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/signin", method = "OPTIONS")
    Empty signinOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return UnFollowResult
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/unfollowuser", method = "POST")
    UnFollowResult unfollowuserPost(
            UnFollowRequest body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/unfollowuser", method = "OPTIONS")
    Empty unfollowuserOptions();
    
}

