package club.towr5291.towr5291relicrecoveryscouting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Andrew on 10/25/2017.
 */

public class PreMatchActivity extends AppCompatActivity {
    public static final String match_number = "key1";
    public static final String scout_name = "key2";
    public static final String team = "key3";
    public static final String array = "key4";
    public static final String scout_team = "key5";

    Spinner spinner_select_team;
    String[] team_array = {"5291", "11230", "11231", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"}; // Pass this to new activities
    String[] team_name_array = {"TOWR", "Elektrakatz", "Cyborg Cats", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
    String selectedTeam;
    String message_team_number;

    String scout = "";
    String scouts_team = "";
    String[] old_data_array0 = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prematch);

        TextView normally_invisible_debug_output = (TextView) findViewById(R.id.normally_invisible_data_output);
        normally_invisible_debug_output.setText(String.valueOf(getFilesDir()));

//        Intent intent = getIntent();
//        scout = getIntent().getStringExtra("scout_name");
//        scouts_team = getIntent().getStringExtra("scouts_team");
//        old_data_array0 = getIntent().getStringArrayExtra("array");
//
//        TextView textView_scout = (TextView) findViewById(R.id.textView_scout);
//        TextView textView_scoutsteam = (TextView) findViewById(R.id.textView_scoutsteam);
//
//        textView_scout.setText(scout);
//        textView_scoutsteam.setText(scouts_team);

        Context context = getApplicationContext();
        CharSequence text = String.valueOf(getFilesDir());
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /** Called when the user taps the GO TO AUTONOMOUS button */
    public void gotoAutonomous(View view) {
        // Do something in response to button

        spinner_select_team = (Spinner)findViewById(R.id.prematch_select_team);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, team_array);

//        spinner_select_team.setAdapter(adapter);

        int spinner_position = spinner_select_team.getSelectedItemPosition();
        message_team_number = team_array[Integer.valueOf(spinner_position)];
        // Text Box
        Intent intent = new Intent(this, AutonomousActivity.class);
        EditText editText_input_match = (EditText) findViewById(R.id.prematch_input_match);
        String message_match_number = editText_input_match.getText().toString();
//        if(editText_input_match.getText().toString() == "") {
//            message_match_number = "0";
//        }

        String[] old_data_array1 = push(old_data_array0, message_match_number);
        String[] old_data_array2 = push(old_data_array1, String.valueOf(message_team_number));
        String[] old_data_array3 = push(old_data_array2, String.valueOf(team_name_array[spinner_position]));
        String[] old_data_array4 = push(old_data_array3, scout);
        String[] data_array = push(old_data_array4, scouts_team);


        String message_team_number = team_array[Integer.valueOf(spinner_position)];
        intent.putExtra("team_number", message_team_number);
        intent.putExtra("match_number", message_match_number);
//        intent.putExtra(spinner_position, message);

        intent.putExtra("scout_name", scout);
        intent.putExtra("scout_team", scouts_team);

//        Bundle bundle1 = new Bundle();
//        bundle1.putString(match_number, message_match_number);
//        bundle1.putString(team, message_team_number);
//        bundle1.putString(scout_name, scout);
//        bundle1.putString(scout_team, scouts_team);
//        intent.putExtras(bundle1);

//        String[] message_array = {message_match_number,message_team_number};


        intent.putExtra("array", data_array);
        startActivity(intent);
    }

    private static String[] push(String[] array, String push) {
        String[] longer = new String[array.length + 1];
        for (int i = 0; i < array.length; i++)
            longer[i] = array[i];
        longer[array.length] = push;
        return longer;
    }


    /** Called when the user taps the GO TO TELEOP button */
//    public void gotoTeleop(View view) {
//        // Do something in response to button
//
//        spinner_select_team = (Spinner)findViewById(R.id.spinner_select_team);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, team_array);
//
////        spinner_select_team.setAdapter(adapter);
//
//        int spinner_position = spinner_select_team.getSelectedItemPosition();
//        message_team_number = team_array[Integer.valueOf(spinner_position)];
//        // Text Box
//        Intent intent = new Intent(this, TeleOpActivity.class);
//        EditText editText_input_match = (EditText) findViewById(R.id.editText_input_match);
//        String message_match_number = editText_input_match.getText().toString();
//        if(message_match_number == "") {
//            message_match_number = "0";
//        }
////        String message_team_number = team_array[Integer.valueOf(spinner_position)];
//        intent.putExtra(match_number, message_match_number);
////        intent.putExtra(spinner_position, message);
//        intent.putExtra(team, message_team_number);
//        String[] message_array = {message_match_number,message_team_number};
//        intent.putExtra(array, message_array);
//        startActivity(intent);
//    }
}