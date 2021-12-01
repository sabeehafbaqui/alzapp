Package com.mypackage.espressotestngui;

import junit.framework.TestCase;

Public class MainActivityTest  {

@Rule
Public ActivityTestRule<MainActivityTestRule = newActivityTestRule<MainActivity>(MainActivity.class);

@Before
Public void setUp() throws Exception  {

}
@Test
Public void testUserInputscenario()

//perform button click
Espresso.onView(withId(R.id.button_start_survey)).perform(click());
//checking the layout is changed
Espresso.onView(withId(R.id.layout_three)).check()matches(withlayouttwo.setVisibilty(view.Visible)

@After
Public void tearDown()throws Exception  {
  }
}

@Rule
Public ActivityTestRule<MainActivityTestRule = newActivityTestRule<MainActivity>(MainActivity.class);


Private String fName = “Sabeeha”;
Private String lName = “Baqui”;
Private String Ssex = “Female”;
Private int Aage = “18”;
Private int HId = “123”;
Private int Pnumber = “4145678”;


@Before
Public void setUp() throws Exception  {

}
@Test
Public void testUserInputscenario()
//input some text in the edit text
Espresso.onView(withId(R.id.et_firstname)).perform(typeText(fName));
//input some text in the edit text
Espresso.onView(withId(R.id.et_lastname)).perform(typeText(lName));
//input some text in the edit text
Espresso.onView(withId(R.id.et_sex)).perform(typeText(Ssex));
//input some text in the edit text
Espresso.onView(withId(R.id.et_age)).perform(typeText(Aage));
//input some text in the edit text
Espresso.onView(withId(R.id.et_hospitalID)).perform(typeText(HId));
//input some text in the edit text
Espresso.onView(withId(R.id.et_phone)).perform(typeText(Pnumber));
//close soft keyboard
Espresso.closeSoftKeyboard();
//perform button click
Espresso.onView(withId(R.id.button_personal_info_next)).perform(click());
//checking the layout is changed
Espresso.onView(withId(R.id.layout_three)).check()matches(withlayoutthree.setVisibilty(view.Visible)


@After
Public void tearDown()throws Exception  {
  }
}


@Rule
Public ActivityTestRule<MainActivityTestRule = newActivityTestRule<MainActivity>(MainActivity.class);


Private int mmse_score = “15”;
Private int moca_score = “30”;
Private String depression = “Negative”;
Private int vitamin_B12 = “18”;
Private String lumbar = “Negative”;



@Before
Public void setUp() throws Exception  {

}
@Test
Public void testUserInputscenario()
//input some text in the edit text
Espresso.onView(withId(R.id.editText4)).perform(typeText(mmse_score));
//input some text in the edit text
Espresso.onView(withId(R.id.editText5)).perform(typeText(moca_score));
//input some text in the edit text
Espresso.onView(withId(R.id.editText6)).perform(typeText(depression));
//input some text in the edit text
Espresso.onView(withId(R.id.editText9)).perform(typeText(vitamin_B12));
//input some text in the edit text
Espresso.onView(withId(R.id.editText7)).perform(typeText(lumbar));
//close soft keyboard
Espresso.closeSoftKeyboard();
//perform button click
Espresso.onView(withId(R.id.button_test_result_next)).perform(click());
//checking the layout is changed
Espresso.onView(withId(R.id.layout_four)).check()matches(withlayoutfour.setVisibilty(view.Visible)

@After
Public void tearDown()throws Exception  {
  }
}

@Rule
Public ActivityTestRule<MainActivityTestRule = newActivityTestRule<MainActivity>(MainActivity.class);


Private int  hopkins = “17”;
@Before
Public void setUp() throws Exception  {

}
@Test
Public void testUserInputscenario()
//input some text in the edit text
Espresso.onView(withId(R.id.editText15)).perform(typeText(hopkins));
//close soft keyboard
Espresso.closeSoftKeyboard();
//perform button click
Espresso.onView(withId(R.id.button_mri_import)).perform(click());
//checking the layout is changed
Espresso.onView(withId(R.id.layout_three)).check()matches(withViewbyId()
//perform button click
Espresso.onView(withId(R.id.button_generate_report)).perform(click());
//checking the layout is changed
Espresso.onView(withId(R.id.layout_three)).check()matches(withVisibilty)

@After
Public void tearDown()throws Exception  {
  }
}
}
