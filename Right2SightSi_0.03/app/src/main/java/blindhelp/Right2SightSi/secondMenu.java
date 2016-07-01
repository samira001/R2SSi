package blindhelp.Right2SightSi;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Locale;

public class secondMenu extends ActionBarActivity implements View.OnClickListener,TextToSpeech.OnInitListener {
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private MediaPlayer mediaPlayer;
    private TextToSpeech textToSpeech;
    private static final long DOUBLE_PRESS_INTERVAL = 250; //interval for double press in millis
    long pressTime;
    private long lastPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_menu);
        getSupportActionBar().hide();// this is the code for the hide applicatoin name
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer=new MediaPlayer();
        textToSpeech=new TextToSpeech(this,this);
        onBackPressed();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);

        Button1 = (Button) findViewById(R.id.button4);
        Button2 = (Button) findViewById(R.id.button3);
        Button3 = (Button) findViewById(R.id.button2);
        Button4 = (Button) findViewById(R.id.button);
        Button5 = (Button) findViewById(R.id.button1);

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);

        Button5.setEnabled(false);
        Button4.setEnabled(false);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
    public void PeraMenuwa(){
        mediaPlayer=MediaPlayer.create(this,R.raw.back);
        mediaPlayer.start();
    }
    public void Ewathwanna() {
        mediaPlayer=MediaPlayer.create(this,R.raw.exit);
        mediaPlayer.start();
    }
    private void Kiyawanna() {
        mediaPlayer=MediaPlayer.create(this,R.raw.battery);
        mediaPlayer.start();
    }

    public void sihikadaveem(){
        mediaPlayer.release();
        //mediaPlayer=MediaPlayer.create(this,R.raw.reminder);
        //mediaPlayer.start();
    }

    private void resetPlayer(){
        mediaPlayer.release();
    }
    @Override
    public void onClick(View v) {
        pressTime = System.currentTimeMillis();
        // If double click...
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:
                    OpenFirstmenu();
                    break;
                case R.id.button3:
                    Reading();
                    break;
                case R.id.button2:
                    Back();
                    break;
                case R.id.button:
                    OpenRemindermenu();
                    break;
                case R.id.button1:
                    Back();
                    break;
            }
        }
        else {
            resetPlayer();
            switch (v.getId()) {
                case R.id.button4:PeraMenuwa();break;
                case R.id.button3:Kiyawanna();break;
                case R.id.button2:Ewathwanna();break;
                case R.id.button:sihikadaveem();break;
                case R.id.button1:Ewathwanna();break;
            }
            lastPressTime = pressTime;
        }
    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
    private void Back() {
     //   mediaPlayer.stop();

        Intent intent = new Intent(getApplicationContext(), secondMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        System.exit(0);

    }

    private void Reading() {
        Intent intent=new Intent(this,TimeDate.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public void onInit(int status) {
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.setSpeechRate(0.8f);
    }

    private void OpenRemindermenu() {
        Intent intent=new Intent(this,Reminder.class);
        startActivity(intent);
        this.finish();
    }

    private void OpenFirstmenu() {
        Intent intent=new Intent(this,FirstMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
