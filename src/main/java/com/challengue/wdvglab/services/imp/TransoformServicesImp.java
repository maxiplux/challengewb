package com.challengue.wdvglab.services.imp;

import com.challengue.wdvglab.models.Phrase;
import com.challengue.wdvglab.models.Transform;
import com.challengue.wdvglab.services.TransoformServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class TransoformServicesImp implements TransoformServices {


    @Override
    public Transform process(Phrase phrase) {

        int countDot = StringUtils.countMatches(phrase.getPhrase(), ".");
        int countSpace = StringUtils.countMatches(phrase.getPhrase(), " ");
        String word = "";

        Function<String,String> auxTransform = value -> {
            Character finalLetter = value.charAt(value.length() - 1);
            Character startLetter = value.charAt(0);

            if (Character.isLetter(finalLetter)) {
                if (Character.isLetter(startLetter)) {
                    return new StringBuilder(value).reverse().toString();

                }


                return new StringBuilder(value.substring(1, value.length() - 1)).reverse().toString();
            }


            return new StringBuilder(value.substring(0, value.length() - 1)).reverse().toString() + value.charAt(value.length() - 1);


        };


        if (countDot > countSpace) {

            word = Arrays.asList(phrase.getPhrase().split("\\.")).stream().map(smallword -> auxTransform.apply(smallword) + ".").collect(Collectors.joining());
        } else {
            word = Arrays.asList(phrase.getPhrase().split(" ")).stream().map(smallword -> auxTransform.apply(smallword) + " ").collect(Collectors.joining());

        }

        return new Transform(word.substring(0, word.length() - 1));
    }

}
