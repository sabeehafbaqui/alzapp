//AlzheimerQuestionnare.java:

package com.example.sabeehabaqui.alzapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AlzheimerQuestionnaire extends AppCompatActivity {

   private int PICK_IMAGE_REQUEST = 1;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_alzheimer_questionnaire);

       //request permissions
       //to write to external drive
       if (checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
           requestPermissions( new String[]{
                   Manifest.permission.READ_EXTERNAL_STORAGE,
                   Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);
       }


       //YOUR CODE GOES HERE
       final LinearLayout layoutone = findViewById(R.id.layout_one);
       final LinearLayout layouttwo = findViewById(R.id.layout_two);
       final LinearLayout layoutthree = findViewById(R.id.layout_three);
       final LinearLayout layoutfour = findViewById(R.id.layout_four);

       layoutone.setVisibility(View.VISIBLE);
       layouttwo.setVisibility(View.GONE);
       layoutthree.setVisibility(View.GONE);
       layoutfour.setVisibility(View.GONE);

       Button button_first = findViewById(R.id.button_start_survey);
       Button layout2_next = findViewById(R.id.button_personal_info_next);
       Button layout3_next = findViewById(R.id.button_test_result_next);
       Button results_button = findViewById(R.id.button_generate_report);

       Button importButton = findViewById(R.id.button_mri_import);
       ImageView mriView = findViewById(R.id.imageView_mri);

       importButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
// Show only images, no videos or anything else
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
               startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
           }
       });



       button_first.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               layoutone.setVisibility(View.GONE);
               layouttwo.setVisibility(View.VISIBLE);

           }
       });


       layout2_next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               layouttwo.setVisibility(View.GONE);
               layoutthree.setVisibility(View.VISIBLE);

           }
       });


       layout3_next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               layoutthree.setVisibility(View.GONE);
               layoutfour.setVisibility(View.VISIBLE);

           }
       });

       results_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               makeReport ();


               //NOTE: this section is for future in case teacher asks to add more features that I have for now
               //TODO:save everything to a file for later use
               HashMap<String, String> data = new HashMap<String, String>();

               //TODO: repeat this for every variable you want to save to the file
               // EditText fullname_et = (EditText) findViewById(R.id.???);
               // data.put("fullname",fullname_et.getText();); //add each data using thins command


               //save data to a file
               try {
                   FileOutputStream fos = openFileOutput("datafile.data", Context.MODE_PRIVATE);
                   ObjectOutputStream out = new ObjectOutputStream(fos);
                   out.writeObject(data);
               }catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });
   } //Just this one

   private String grabTextFromTextView (int id) {
       EditText tv = (EditText) findViewById(id);
       String text = tv.getText().toString();
       return text;
   }

   private void makeReport() {
       // create a new document
       PdfDocument document = new PdfDocument();

       // crate a page description
       PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(800,1150,1).create();

       // start a page
       PdfDocument.Page page = document.startPage(pageInfo);

       // draw something on the page
       //View content = getContentView();
       Canvas canvas = page.getCanvas();

       int x = 100;
       int y = 1000;
       int size = 20;


       //layout.draw(canvas);
       Paint paint = new Paint();
       paint.setColor(Color.BLACK);
       paint.setTextSize(20);
       paint.setTextAlign(Paint.Align.LEFT);

       String first_name = grabTextFromTextView(R.id.et_firstname);
       String last_name = grabTextFromTextView(R.id.et_lastname);
       String sex = grabTextFromTextView(R.id.et_sex);
       int age = Integer.parseInt(grabTextFromTextView (R.id.et_age));
       String hospital_id = grabTextFromTextView(R.id.et_hospitalID);
       String phone = grabTextFromTextView(R.id.et_phone);

       int mmse_score = Integer.parseInt(grabTextFromTextView (R.id.editText4));
       int moca_score = Integer.parseInt(grabTextFromTextView (R.id.editText5));
       int depression = Integer.parseInt(grabTextFromTextView (R.id.editText6));
       int vitamin_B12 = Integer.parseInt(grabTextFromTextView (R.id.editText9));
       String lumbar = (grabTextFromTextView (R.id.editText7));
       int hopkins = Integer.parseInt(grabTextFromTextView (R.id.editText15));

       //show patient info
       paint.setTextSize(20);
       canvas.drawText("First Name: " + first_name, 50,50, paint);
       canvas.drawText("Last Name: " + last_name, 50, 100, paint);
       canvas.drawText("Sex: " + sex, 50,150, paint);
       canvas.drawText("Age: " + age, 50, 200, paint);
       canvas.drawText("Hospital ID: " + hospital_id, 50, 250, paint);
       canvas.drawText("Phone Number: " + phone, 50, 300, paint);

       //show scores
       paint.setTextSize(20);
       canvas.drawText("MMSE Score: " + mmse_score, 50, 350, paint);
       canvas.drawText("MOCA Score: " + moca_score, 50, 400, paint);
       canvas.drawText("depression Score: " + depression, 50, 450, paint);
       canvas.drawText("Vitamin B12 level: " + vitamin_B12, 50, 500, paint);
       canvas.drawText("Lumbar Puncture findings: " + lumbar, 50, 550, paint);
       canvas.drawText("Hopkins Verbal Learning Score: " + hopkins, 50, 600, paint);

       //draw image
       try {
           Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_uri);
           Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 200, false);

           canvas.drawBitmap(bitmap2, 50, 650, paint);
       } catch (Exception e) {
           e.printStackTrace();
           canvas.drawText("NO MIR IMAGE AVAILABLE", 50, 650, paint);
       }



       boolean hasDementia = false;
       if (mmse_score < 25 || moca_score < 24) {
           hasDementia = true;
       }

       boolean pseudoDementia = false;
       if (vitamin_B12 < 200 || depression < 50) {
           pseudoDementia = true;
       }

       paint.setTextSize(20);

       if(hasDementia && !pseudoDementia){
           Log.i("MESSAGE","1");
           canvas.drawText("You may be showing signs of dementia. " , 50,900, paint);
           canvas.drawText("Please consult a neurologist with a specialization in dementia to further continue diagnosis.", 50,950, paint);
       }
       else if (hasDementia && pseudoDementia) {
           Log.i("MESSAGE","2");
           canvas.drawText("You may have pseudodementia. " , 50, 900, paint);
           canvas.drawText("We advice you check your blood work and see a psychiastist.", 50, 950, paint);
       }
       else if(!hasDementia && pseudoDementia)
       {
           Log.i("MESSAGE","3");
           canvas.drawText("You do not have dementia. " , 50, 900, paint);
           canvas.drawText("However, we strongly advice that you follow with your family doctor", 50, 950, paint);
           canvas.drawText("to address incidental findings.", 50, 1000, paint);
       }
       else
       {
           Log.i("MESSAGE","4");
           canvas.drawText("You are healthy, go live a happy life.", 50, 900, paint);
       }



                /*
               case 1:
                   y += (paint.descent() - paint.ascent()) * 2.5;
                   score = Double.parseDouble (results.get(i));
                   canvas.drawText("MOCA Score: " + results.get(i), x, y, paint);
                   if (score < 24) {
                       String text = ("You may be showing signs of dementia. " +
                               "Please consult a neurologist with a specialization in dementia to further continue diagnosis. ");

                       y += (paint.descent() - paint.ascent()) * 2.5;
                       canvas.drawText(text, x, y, paint);
                   }
                   paint.setTextSize(size / 1.3f);
                   break;
               case 2:
                   y += (paint.descent() - paint.ascent()) * 2.5;
                   canvas.drawText("Depression: " + results.get(i), x, y, paint);
                   if ("Depression:positive".equals(results.get(i))) {
                       String text = "Your depression may be causing dementia_like symptoms. " +
                               "Please consult a neurologist with a specialization in dementia to further continue diagnosis. ";

                       y += (paint.descent() - paint.ascent()) * 2.5;
                       canvas.drawText(text, x, y, paint);
                   }
                   break;
               case 3:
                   y += (paint.descent() - paint.ascent()) * 2.5;
                   score = Double.parseDouble (results.get(i));
                   canvas.drawText("Vitamin B12: " + results.get(i), x, y, paint);
                   if (score < 200) {
                       String text = "You may be showing signs of a Vitamin B12 deficiency, which can interfere with your memory. " +
                               "Please consult a neurologist with a specialization in dementia to further continue diagnosis.";

                       y += (paint.descent() - paint.ascent()) * 2.5;
                       canvas.drawText(text, x, y, paint);
                   }
                   paint.setTextSize(size / 1.3f);

                   break;
               default:
                   break;
           }
       }
       */


       // finish the page
       document.finishPage(page);

       // write the document content
       // generate a time stamp

       try
       {
           //save to a file
           File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                   + "/filename.pdf");
           FileOutputStream outstream = new FileOutputStream(file.getAbsolutePath());
           document.writeTo(outstream);

           //try to open the file
           Log.i("ASD", Uri.fromFile(file).toString());
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setData(Uri.fromFile(file));
           intent.setType("application/pdf");
           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(intent);
       }
       catch(IOException ex)
       {
           Log.e("YOUR_APP_LOG_TAG", "I got an error", ex);
           Toast.makeText(this, "ERROR: Cannot write to storage.",
                   Toast.LENGTH_SHORT).show();
       }
       catch (ActivityNotFoundException e) {
           Toast.makeText(this, "no app to open pdf files. check your external memory for report.",
                   Toast.LENGTH_SHORT).show();
       }


       // close the document
       document.close();
   }


   Uri image_uri;

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

           Uri uri = data.getData();

           image_uri = uri;
           try {
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
               // Log.d(TAG, String.valueOf(bitmap));

               ImageView imageView = findViewById(R.id.imageView_mri);
               imageView.setImageBitmap(bitmap);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}




