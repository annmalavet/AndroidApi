package com.marvel.malavet.marvel;
import com.marvel.malavet.marvel.Models.ComicBookObject.Data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.HashMap;

import javax.inject.Inject;


@RunWith(MockitoJUnitRunner.class)
public class MainTest {


    @Test
    public void checkParamNotNull() {
        assertTrue(APIHash.checkParam());
    }
}