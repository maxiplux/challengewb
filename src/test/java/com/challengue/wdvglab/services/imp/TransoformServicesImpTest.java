package com.challengue.wdvglab.services.imp;

import com.challengue.wdvglab.models.Phrase;
import com.challengue.wdvglab.models.Transform;
import com.challengue.wdvglab.services.TransoformServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;

import static org.junit.Assert.*;


/*
- Reverses the individual words in the phrase (while leaving punctuation in place):

    `test` -> `test`
    `Hello World!` -> `olleH dlroW!`
    `ab.cd.ef.gh.ij` -> `ba.dc.fe.hg.ji`
    `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.` -> `meroL muspi rolod tis tema, rutetcesnoc gnicsipida tile, des do domsuie ropmet tnudidicni ut erobal te erolod angam auqila.`

- Returns the result with the following format:

  ```javascript
  {
    "esarhp": <string>
  }
  ```

*/
public class TransoformServicesImpTest {
    private  ArrayList<Pair<String,String>> dataset;


    @InjectMocks
    private TransoformServices service = new TransoformServicesImp();

    @Before
    public void setUp() throws Exception {

        dataset=new ArrayList<Pair<String,String>>();

        dataset.add( new Pair<String, String>("test","tset"));
        dataset.add( new Pair<String, String>("Hello World!","olleH dlroW!"));

        dataset.add( new Pair<String, String>("ab.cd.ef.gh.ij","ba.dc.fe.hg.ji"));

        dataset.add( new Pair<String, String>("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","meroL muspi rolod tis tema, rutetcesnoc gnicsipida tile, des od domsuie ropmet tnudidicni tu erobal te erolod angam auqila."));


    }

    @Test
    public void process() {
        this.dataset.stream().forEach(pair  -> {
            Transform transform=service.process(new Phrase(pair.getLeft()));
            Assert.assertEquals( transform.getEsarhp()  ,pair.getRight());
        } );
    }

    @Test
    public void auxTransform() {
    }
}