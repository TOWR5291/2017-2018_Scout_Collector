package club.towr5291.towr5291relicrecoveryscouting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinalizeActivity extends AppCompatActivity {

    String match_number;
    String team_number;
    String[] old_data_array0 = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

        Intent intent = getIntent();
        match_number = intent.getStringExtra("match_number");
        team_number = intent.getStringExtra("team_number");

        old_data_array0 = intent.getStringArrayExtra("array");

        TextView finalize_team_number = (TextView) findViewById(R.id.finalize_team_number);
        finalize_team_number.setText("Team: " + team_number);

        TextView finalize_match_number = (TextView) findViewById(R.id.finalize_match_number);
        finalize_match_number.setText("Match: " + match_number);
    }

    public void onFinish(View view) {
        EditText finalize_comment = (EditText) findViewById(R.id.finalize_comment);
        String[] data_array = push(old_data_array0, finalize_comment.getText().toString());

        Context context = getApplicationContext();

        //file save stuff
//        String fileName = "match" + match_number + "team" + team_number;
        String fileName = "output.txt";
        String content = "";

        for (int i = 0; i< data_array.length; i++) {
            if(i + 1 == data_array.length) {
                content += data_array[i];
            } else {
                content += (data_array[i] + ",");
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(content.getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(),"Message Saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String[] push(String[] array, String push) {
        String[] longer = new String[array.length + 1];
        for (int i = 0; i < array.length; i++)
            longer[i] = array[i];
        longer[array.length] = push;
        return longer;
    }

    private static int toInt(boolean variable) {
        if(variable == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
