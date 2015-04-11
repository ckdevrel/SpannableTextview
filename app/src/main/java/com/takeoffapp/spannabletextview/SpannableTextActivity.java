package com.takeoffapp.spannabletextview;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class SpannableTextActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private RadioButton radioColor;
    private RadioButton radioSize;
    private RadioButton radioStyle;
    private RadioButton radioClick;

    private EditText edtFirst;
    private EditText edtSecond;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_span_text);
        findViews ();
    }

    private void findViews() {
        txt1 = (TextView)findViewById( R.id.txt1 );
        txt2 = (TextView)findViewById( R.id.txt2 );
        txt3 = (TextView)findViewById( R.id.txt3 );


        edtFirst = (EditText)findViewById( R.id.edt_first );
        edtSecond = (EditText)findViewById( R.id.edt_second );
        radioColor = (RadioButton)findViewById( R.id.radio_color );
        radioSize = (RadioButton)findViewById( R.id.radio_size );
        radioStyle = (RadioButton)findViewById( R.id.radio_style );
        radioClick = (RadioButton)findViewById( R.id.radio_click );

        radioColor.setOnClickListener( this );
        radioSize.setOnClickListener( this );
        radioStyle.setOnClickListener( this );
        radioClick.setOnClickListener (this);
    }


    private void doColorSpanForFirstString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (firstString != null ? firstString : "");
        //        String totalString = " has accepted your friend request. You have received 100 chips";
        String totalString = changeString + lastString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new ForegroundColorSpan (getResources ().getColor (R.color.orange)), 0, changeString.length (), 0);
        txtSpan.setText (spanText);

    }

    private void doColorSpanForSecondString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (lastString != null ? lastString : "");
        String totalString = firstString + changeString;
        Spannable spanText = new SpannableString (totalString);

        spanText.setSpan (new ForegroundColorSpan (getResources ().getColor (R.color.orange)), String.valueOf (firstString).length (), totalString.length (), 0);
        txtSpan.setText (spanText);
    }

    private void doSizeSpanForFirstString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (firstString != null ? firstString : "");
        String totalString = changeString + lastString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new ForegroundColorSpan (getResources ().getColor (R.color.green)), 0, changeString.length (), 0);
        spanText.setSpan (new RelativeSizeSpan (1.5f), 0, changeString.length (), 0);

        txtSpan.setText (spanText);

    }

    private void doSizeSpanForSecondString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (lastString != null ? lastString : "");
        String totalString = firstString + changeString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new ForegroundColorSpan (getResources ().getColor (R.color.green)), String.valueOf (firstString).length (), totalString.length (), 0);
        spanText.setSpan (new RelativeSizeSpan (1.5f), String.valueOf (firstString).length (), totalString.length (), 0);

        txtSpan.setText (spanText);
    }

    private void doStyleSpanForFirstString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (firstString != null ? firstString : "");
        String totalString = changeString + lastString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new StyleSpan (Typeface.BOLD), 0, changeString.length (), 0);

        txtSpan.setText (spanText);

    }

    private void doStyleSpanForSecondString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (lastString != null ? lastString : "");
        String totalString = firstString + changeString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new StyleSpan (Typeface.BOLD), String.valueOf (firstString).length (), totalString.length (), 0);
        txtSpan.setText (spanText);
    }

    private void doClickSpanForString (String firstString, String lastString,TextView txtSpan) {
        String changeString = (lastString != null ? lastString : "");
        String totalString = firstString + changeString;
        Spannable spanText = new SpannableString (totalString);
        spanText.setSpan (new MyClickableSpan (totalString), String.valueOf (firstString).length (), totalString.length (), 0);
        txtSpan.setText (spanText);
        txtSpan.setMovementMethod(LinkMovementMethod.getInstance ());
    }
    @Override
    public void onClick (View v) {

        String firstString = edtFirst.getText ().toString ().trim ();
        String lastString  = edtSecond.getText ().toString ().trim ();

        if ( v == radioColor ) {
            // Handle clicks for radioColor
            txt3.setVisibility (View.GONE);

            doColorSpanForFirstString (firstString, " "+lastString, txt1);
            doColorSpanForSecondString (firstString, " " + lastString, txt2);

        } else if ( v == radioSize ) {
            // Handle clicks for radioSize
            txt3.setVisibility (View.GONE);

            doSizeSpanForFirstString (firstString, " " + lastString, txt1);
            doSizeSpanForSecondString (firstString, " "+lastString,txt2);

        } else if ( v == radioStyle ) {
            // Handle clicks for radioStyle
            txt3.setVisibility (View.GONE);

            doStyleSpanForFirstString (firstString, " " + lastString, txt1);
            doStyleSpanForSecondString (firstString, " "+lastString,txt2);
        } else if ( v == radioClick) {
            txt3.setVisibility (View.VISIBLE);
            doClickSpanForString ("Please Click ", "here", txt3);
        }
    }


    class MyClickableSpan extends ClickableSpan {
        public MyClickableSpan(String string)
        {
            super();
        }
        public void onClick(View tv)
        {

            Toast.makeText (SpannableTextActivity.this, "Thanks for the click!", Toast.LENGTH_SHORT).show ();
        }
        public void updateDrawState(TextPaint ds)
        {
            ds.setColor(getResources().getColor(R.color.material_indigo_dark));//set text color
            ds.setUnderlineText(true); // set to false to remove underline
        }
    }
}
