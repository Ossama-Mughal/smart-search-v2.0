package info.androidhive.materialtabs.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.URI;

import info.androidhive.materialtabs.R;





public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }

    @Nullable

    Uri contactInfo;
    URI contactinfo;
    Context c;
    info.androidhive.materialtabs.activity.soundex s;
    static  String []phone;
    static String []name;
    static String []soundex;
    static int []type;
    TableLayout tl;
    TableRow tr;
    TextView nameView;
    TextView phoneView;
    int Contactscount=0;
    int count=0;
    Cursor cursor;
    Button searchBtn;
    View.OnClickListener listener;
    EditText edit;
    android.support.v7.app.AlertDialog alert;
    ImageView img;
    ScrollView sc;

    public void onAttach(Activity activity){
        super.onAttach(activity);

        cursor = activity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        c=activity;


    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        c=getActivity();
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        edit=(EditText)view.findViewById(R.id.editText);
        sc=(ScrollView)view.findViewById(R.id.scrollView);
        img=new ImageView(getActivity());

        TableRow.LayoutParams l=new TableRow.LayoutParams(70,70);
        img.setLayoutParams(l);
        img.setPadding(0, 0, 45, 0);
        img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        // searchBtn=(Button)view.findViewById(R.id.new_btn);
        //searchBtn.setOnClickListener(listener);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e("string", name[4]);
                Log.e("string", "sads");
                // switch(v.getId()) {
                //  case R.id.new_btn:
                Log.e("string", name[4]);
                EditText e = (EditText) getActivity().findViewById(R.id.editText);
                String sub = e.getText().toString();

                if (sub.length() > 1) {

                    info.androidhive.materialtabs.activity.soundex  s1;
                    s1 = new   info.androidhive.materialtabs.activity.soundex();
                    String sx = s1.soundex(sub);


                    tl.removeAllViews();
                    for (int i = 0; i < Contactscount; i++) {

                        if (soundex[i] != "" && soundex[i] != null) {
                            if (soundex[i].contains(sx)) {
                                Log.e("string", name[i]);
                                Log.e("Sub", sub);
                                img=new ImageView(getActivity());
                                //  img=new ImageView(getActivity());

                                TableLayout.LayoutParams l=new TableLayout.LayoutParams(70,70);
                                //  l.width=15;
                                img.setLayoutParams(l);
                                img.getLayoutParams().width=150;

                                // img.setMaxWidth(25);
                                // left , top, rt , btm
                                img.setPadding(40, 0, 0, 0);
                                img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                                img.setMaxHeight(100);
                                //  tr.addView(img);


                                //  tr.addView(nameView);
                                tl.addView(img);

                                tr = new TableRow(getActivity());
                                tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                                nameView = new TextView(getActivity());

                                nameView.setText(name[i]);
                                nameView.setHeight(40);
                                nameView.setTextSize(22);
                                nameView.setPadding(20, 30, 0, 0);
                                nameView.setWidth(48);
                                nameView.setTextSize(20);
                                nameView.setHeight(70);

                                tr.addView(nameView);


                                phoneView = new TextView(getActivity());
                                phoneView.setText(phone[i]);
                                phoneView.setPadding(0, 30, 10, 0);

                                //phoneView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                                phoneView.setTextSize(20);
                                phoneView.setHeight(70);


                                //  phoneView.setTextSize(18);
                                tr.addView(phoneView);
                                tr.setTag(i);
                                tr.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        TableRow tr = (TableRow) v;
                                        Object i = tr.getTag();
                                        TextView n1 = (TextView) tr.getChildAt(0);
                                        TextView p1 = (TextView) tr.getChildAt(1);
                                        Log.e(n1.getText().toString(), "name");
                                        String number = n1.getText().toString().trim();
                                        number = "tel:" + number;

                                        //   Intent CallIntent=new Intent(Intent.ACTION_CALL,Uri.parse(number));
                                        // CallIntent.setData(Uri.parse(number));
                                        //   startActivity(CallIntent);

                                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(c);
                                        builder.setMessage("name: " + n1.getText().toString() + "    Phone:" + p1.getText().toString());
                                        //  alert.setMessage("name: " + n1 + "    Phone:" + p1);
                                        alert = builder.create();
                                        alert.show();

                                    }
                                });


                                tl.addView(tr, new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT));


                                View v = new View(c);
                                v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                                v.setBackgroundColor(Color.rgb(51, 51, 51));
                                tl.addView(v);
                            }
                        }

                    }
                } else {
                    tl.removeAllViews();
                    AddData();
                }

                Log.e("string", name[4]);
                //   break;
                //  }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });






        ReadPhoneContacts(getActivity());
         Display();
        // Log.e(name[1], "contact name--!!!");
        tl=(TableLayout)view.findViewById(R.id.contact);

        AddData();
        return view;

    }


    public void onClick(View v) {

    }





    public void search(View v){

    }

    public void AddData()
    {

        tr = new TableRow(getActivity());
        tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
        nameView = new TextView(getActivity());
        TableRow.LayoutParams np=new TableRow.LayoutParams(40,40);
        np.setMargins(0, 0, 0, 0);
        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        nameView.setLayoutParams(np);
        nameView.setText("Contact");
        //  nameView.setLayoutParams(new Layo);
        nameView.setHeight(40);
        nameView.setTextSize(30);
        nameView.setPadding(20, 15, 0, 0);
        nameView.setWidth(25);

        nameView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

/*
        tr.addView(nameView);


        phoneView = new TextView(getActivity());
        phoneView.setText("Contact Number");
        phoneView.setPadding(20,15,0,0);
        phoneView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        phoneView.setTextSize(18);
        tr.addView(phoneView);
        tl.addView(tr, new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT));

        View v = new View(c);
        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
        v.setBackgroundColor(Color.rgb(51, 51, 51));
        tl.addView(v);
        */
        for(int i=0;i<130;i++) {



            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
            //  TableLayout.LayoutParams=new TableLayout.LayoutParams();
            nameView = new TextView(getActivity());
            if(name[i]==""){
                name[i]="123";
            }
            np.setMargins(0, 0, 40, 0);
            nameView.setText(name[i]);
            nameView.setLayoutParams(np);

            nameView.setWidth(48);
            nameView.setTextSize(20);
            nameView.setHeight(100);
            //  nameView.setTextColor(Color.parseColor("A31F1F"));


            nameView.setPadding(40, 15, 0, 0);


            //  nameView.setLayoutParams(new Layo);
            if(i<200) {
                img=new ImageView(getActivity());
                //  img=new ImageView(getActivity());

                TableLayout.LayoutParams l=new TableLayout.LayoutParams(70,70);
                //  l.width=15;
                img.setLayoutParams(l);
                img.getLayoutParams().width=150;

                // img.setMaxWidth(25);
                // left , top, rt , btm
                img.setPadding(40, 0, 0, 0);
                img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                img.setMaxHeight(100);
                //  tr.addView(img);


                tr.addView(nameView);
                tl.addView(img);
            }


            phoneView = new TextView(getActivity());
            phoneView.setText(phone[i]);
            phoneView.setPadding(0, 15, 0, 0);
            phoneView.setWidth(40);
            phoneView.setTextSize(20);
            tr.addView(phoneView);
            tr.setTag(i);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableRow tr = (TableRow) v;
                    Object i=tr.getTag();
                    TextView n1=(TextView)tr.getChildAt(0);
                    TextView p1=(TextView)tr.getChildAt(1);
                    Log.e(n1.getText().toString(), "name");

                    android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(c);
                    builder.setMessage("name: " + n1.getText().toString() + "    Phone:" + p1.getText().toString());
                    //  alert.setMessage("name: " + n1 + "    Phone:" + p1);
                    alert=builder.create();
                    alert.show();
                    //  tr.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                }
            });
            tl.addView(tr, new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT));
            // tl.addView(tr);

            View v1 = new View(c);
            v1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
            v1.setBackgroundColor(Color.rgb(51, 51, 51));
            tl.addView(v1);
        }


    }


    public void Display(){

        int c=48;

        for(int i=0 ; i <= c  ; i++){

            Log.e(name[i], "contact name");

            Log.println(type[i], "phone type", "phone type");

            Log.e(phone[i], "contact number");

        }

    }

    public void update(View v) {
        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        // startActivityForResult(intent, 1);
        AddData();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, 1);
        // ContentResolver cr = getContentResolver();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // super.onActivityResult(requestCode,resultCode,data);
        // PhoneContacts phoneContacts = new PhoneContacts(service);
        // phoneContacts.ReadPhoneContacts(cntx);

        // if(requestCode==1){contactInfo=data.getData();}


    }


    public void ReadPhoneContacts(Context cntx) //This Context parameter is nothing but your Activity class's Context
    {

        //  MainActivity main=new MainActivity();
        //  Cursor cursor = main.setCursor();
        Integer contactsCount = cursor.getCount(); // get how many contacts you have in your contacts list
        phone=new String[contactsCount];
        name=new String[contactsCount];
        soundex=new String[contactsCount];
        type=new int[contactsCount];
        Contactscount=contactsCount;
        int count=0;

        if (contactsCount > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //the below cursor will give you details for multiple contacts
                    Cursor pCursor = cntx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    name[count]=contactName;


                    //   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    //  SharedPreferences.Editor editor = preferences.edit();
                    //  editor.putString("Name","Harneet");
                    //  editor.apply();





                    // continue till this cursor reaches to all phone numbers which are associated with a contact in the contact list
                    while (pCursor.moveToNext()) {
                        int phoneType = pCursor.getInt(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));


                        //String isStarred 		= pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STARRED));
                        String phoneNo = pCursor.getString(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //you will get all phone numbers according to it's type as below switch case.
                        //Logs.e will print the phone number along with the name in DDMS. you can use these details where ever you want.


                        phone[count]=phoneNo;



                        switch (phoneType) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                Log.e(contactName + ": TYPE_MOBILE", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                Log.e(contactName + ": TYPE_HOME", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                Log.e(contactName + ": TYPE_WORK", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                                Log.e(contactName + ": TYPE_WORK_MOBILE", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                Log.e(contactName + ": TYPE_OTHER", " " + phoneNo);
                                break;
                            default:
                                break;
                        }
                    }
                    pCursor.close();
                    count++;
                }
            }

            cursor.close();
        }

        //  com.example.halfm_1.tabbedview.soundex s;
        s=new   info.androidhive.materialtabs.activity.soundex();

        for(int i=0;i<120;i++){
            soundex[i]=s.soundex(name[i]);
            Log.e(soundex[i],"soundex of");


        }
    }



}
