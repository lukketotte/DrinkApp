package com.example.android.drinkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class ChangeInventoryActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "com.example.android.drinkapp.TYPE";
    public static final String EXTRA_PROD = "com.example.android.drinkapp.PROD";
    public static final String EXTRA_DESC = "com.example.android.drinkapp.DESC";
    public static final String EXTRA_FLAG = "com.example.android.drinkapp.FLAG";

    //this class will eventually create a BarObject
    BarObject barObject;
    //create objects to hold the references to the views on this activity
    EditText itemTypeEdit;
    EditText producerEdit;
    EditText descriptionEdit;
    CheckBox contentFlagBox;
    Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_inventory);

        //create instances and set references for the views
        barObject = new BarObject();
        itemTypeEdit = findViewById(R.id.item_type_name);
        producerEdit = findViewById(R.id.item_producer_name);
        descriptionEdit = findViewById(R.id.item_description);
        contentFlagBox = findViewById(R.id.checkBoxContentFlag);
        addItemButton = findViewById(R.id.add_item_to_inventory);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //step one should be to check whether the user has entered
                //any information into type.
                //TODO(wrong intent): why does this line take me to main and not InventoryActivity
                Intent replyIntent = new Intent(v.getContext(), InventoryActivity.class);

                if(TextUtils.isEmpty(itemTypeEdit.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else{
                    // read in all the information from the textedits and checkbox
                    String stringType = itemTypeEdit.getText().toString();
                    String stringProducer = producerEdit.getText().toString();
                    String stringDescription = descriptionEdit.getText().toString();
                    boolean flagContent;
                    if(contentFlagBox.isChecked()){
                        flagContent = true;
                    } else {
                        flagContent = false;
                    }

                    //stackOverflow, pass data between intents
                    Bundle extras = new Bundle();
                    extras.putString(EXTRA_TYPE, stringType);
                    extras.putString(EXTRA_PROD, stringProducer);
                    extras.putString(EXTRA_DESC, stringDescription);
                    extras.putBoolean(EXTRA_FLAG, flagContent);

                    replyIntent.putExtras(extras);

                    setResult(RESULT_OK, replyIntent);
                }
                startActivity(replyIntent);
                //finish();
            }
        });
    }
}
