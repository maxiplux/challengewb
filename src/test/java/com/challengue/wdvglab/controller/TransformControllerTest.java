package com.challengue.wdvglab.controller;

import com.challengue.wdvglab.WdvglabApplication;
import com.challengue.wdvglab.errors.ValidationError;
import com.challengue.wdvglab.models.Phrase;
import com.challengue.wdvglab.models.Transform;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;



/*
- Validates the request using the following rules, and returns an appropriate HTTP error response:

        - The request can not be empty/null
        - Content-Type must be application/json
        - Phrase can not be empty/null
        - Phrase must be < 1024 characters in length
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WdvglabApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransformControllerTest {

    private  final String BASE_URL = "/api/v1/reverse/";

    @Autowired
    TestRestTemplate restTemplate;

    HttpEntity<Phrase> entity;
    private  String badString1024;


    @Before
    public void setUp(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //headers.add("Authorization","Bearer "+token);
        entity = new HttpEntity<>(new Phrase("test"), headers);
        badString1024=   "qGvspdFhZKRQ22UGbSwmeQWqUtm4BjqGC1Pu7Ph2MGmxDKoRXEaxDAzu1mu6GbAimOlaGQSfBLKkVri3E4OO0mnuSHtQV9l9qIDxNhYWSDPTpzgupmGPbg4vo3JdTt3oTsMXTkAHjXpc1W5noPg6bVFBhQYn1AA7T4R97o9ybHTR1ToEkiXibx71csNGabtxZglzj0wyzEuddfEJtTxGjgJnes6aaMRHGE9olKezUrM6BHLND1kJLw3S3R4j3pDsSkrXhffijzX6dlZCdNPzA72d6jTlBzA2EXyqL0EgNQ2CAJ5VTg0mGBUGnPso533PqrXUX8rcIjeH84NdlhENmCFnLjazj5AqzexF4FqcuNZT3ZXt8pcwUc4HH3HaH8XYkzv2SCeeinSWf0fbKGxh3tH46tWGdFeG4BIy5ZS2gzEDQUDhtg1nAnKvqnJFV3UQqK0jO7MQgC6iVfsZ4an0yK4rIY9igfo7hoEGvSyhbAGI370keurilngYdJ8w6n8rpEwD0LWukfLfIV1oR4wVHRskldd9pbweTIUeQG10wGZJVH3zF5H7k55RCqY600mLQg4palnzF2BPHhXkGMbtJAWFrmtSGEFMsZeTySCwPI25rq9CH5UvPqL6BXBPMm4QPyJttIf8cGZ3rumpuIKLR7UUzKQkX3vvkqR0ArgqcgdvExJJRZXOfj036efbTVUek2yejYwPO4YO3LY4Tdv1z25RtRwKXgJOzZ6if1k7II2PBUr03gIPMXKaLJ4hPQEQKq3GSkrbRBVl4BpCPl0XtU7nyxXkMiH8WfHBZhZo8I7yWbgRu1Z0weGHBPrMeMqycqkjCkoAEsLUgwHqq0idYwryQqUyLUAw9QCAsbABghQpnPaUD1oxMTi8H7g7LHs9iBB11mw30eYhOlFMT3VXtjmzPxFLppm9jS82gBXMjNz6TBCaNNdiSBAFLJUwPPpwv39EhLZK65wLXbwHd6bVdLWzv47vwR4zeWINTYbGySoPMCLMAoaK17cjPCCqBnDm";

    }



    @Test
    public void translatePhraseTest() {
        ResponseEntity<Transform> result = restTemplate.postForEntity(BASE_URL+"/", entity, Transform.class);
        Transform transformResponse = result.getBody();
        Transform transformToTest = new Transform("tset");
        Assert.assertEquals(transformResponse.getEsarhp(), transformToTest.getEsarhp());

    }
    // - The request can not be empty/null
    @Test
    public void translatePhraseRequestEmptyTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.postForEntity(BASE_URL+"/", new HttpEntity<>(new Phrase(), headers), String.class);
        System.out.print(result.getBody());
        Assert.assertEquals(result.getStatusCodeValue(), 400);

    }


    // - Content-Type must be application/json
    @Test
    public void translatePhraseRequestNotNull() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/ecmascript");

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.postForEntity(BASE_URL+"/", new HttpEntity<>(null, headers), String.class);

        System.out.print(result.getBody());
        Assert.assertEquals(result.getStatusCodeValue(), 415);

    }


    // - Phrase can not be empty/null
    @Test
    public void translatePhraseRequestOnlyJsonTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.postForEntity(BASE_URL+"/", new HttpEntity<>(null, headers), String.class);
        Assert.assertEquals(result.getStatusCodeValue(), 400);
    }

    // - Phrase must be < 1024 characters in length
    @Test
    public void translatePhraseRequestBadString() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.postForEntity(BASE_URL+"/", new HttpEntity<>(new Phrase(badString1024), headers), String.class);
        Assert.assertEquals(result.getStatusCodeValue(), 400);
    }


}