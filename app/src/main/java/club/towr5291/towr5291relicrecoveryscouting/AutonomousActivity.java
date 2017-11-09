package club.towr5291.towr5291relicrecoveryscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class AutonomousActivity extends AppCompatActivity {

    int autonomousGlyphs = 0;

    String match_number;
    String team_number;

    String scout;
    String scouts_team;

    String[] old_data_array0 = {};

    public static final String array = "club.towr5291.towr5291relicrecoveryscouting.MESSAGE";
    public static final String matchNumber = "club.towr5291.towr5291relicrecoveryscouting.MESSAGE";
    public static final String teamNumber = "club.towr5291.towr5291relicrecoveryscouting.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        old_data_array0 = intent.getStringArrayExtra("array");
        match_number = intent.getStringExtra("match_number");
        team_number = intent.getStringExtra("team_number");
//        String match_number = intent.getStringExtra(PreMatchActivity.match_number);
//        String team_string = intent.getStringExtra(PreMatchActivity.team);
//        String spinner_position = intent.getStringExtra(PreMatchActivity.spinner_position);

        // Capture the layout's TextView and set the string as its text
        TextView textView_match_number = (TextView) findViewById(R.id.textView_match_number);
        textView_match_number.setText("Match: " + match_number);
//        match_number = Integer.valueOf(data_array[0]);

        TextView textView_team_number = (TextView) findViewById(R.id.textView_team_number);
        textView_team_number.setText("Team: " + team_number);
//        team_number = Integer.valueOf(data_array[1]);

        enableDisableAutonomous(false);
        TextView autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
        autonomous_glyphs_display.setText("0");
    }

//    CheckBox autonomous_balanced_checkbox = (CheckBox) findViewById(R.id.autonomous_robot_balanced);
//
//    Button autonomous_glyphs_up = (Button) findViewById(R.id.autonomous_glyphs_up);
//    Button autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
//    TextView autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
//    CheckBox autonomous_key_bonus = (CheckBox) findViewById(R.id.autonomous_key_bonus);
//
//    CheckBox autonomous_own_jewel = (CheckBox) findViewById(R.id.autonomous_own_jewel);
//    CheckBox autonomous_other_jewel = (CheckBox) findViewById(R.id.autonomous_other_jewel);
//
//    CheckBox autonomous_safe_zone = (CheckBox) findViewById(R.id.autonomous_safe_zone);

    public void onAutonomousBalancedClicked(View view) {
        CheckBox autonomous_balanced_checkbox = (CheckBox) findViewById(R.id.autonomous_robot_balanced);

        boolean checked = autonomous_balanced_checkbox.isChecked();
        enableDisableAutonomous(checked);

        Button autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
        autonomous_glyphs_down.setEnabled(false);

    }

    public void onAutonGlyphsUpClicked(View view) {
        TextView autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
        autonomousGlyphs++;
        autonomous_glyphs_display.setText(String.valueOf(autonomousGlyphs));

        Button autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
        if (autonomousGlyphs != 0) {
            autonomous_glyphs_down.setEnabled(true);
        }
    }

    public void onAutonGlyphsDownClicked(View view) {
        TextView autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
        autonomousGlyphs--;
        autonomous_glyphs_display.setText(String.valueOf(autonomousGlyphs));

        Button autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
        if (autonomousGlyphs == 0) {
            autonomous_glyphs_down.setEnabled(false);
        }
    }

    public void gotoTeleop(View view) {

        CheckBox autonomous_balanced_checkbox = (CheckBox) findViewById(R.id.autonomous_robot_balanced);

        CheckBox autonomous_key_bonus = (CheckBox) findViewById(R.id.autonomous_key_bonus);

        CheckBox autonomous_own_jewel = (CheckBox) findViewById(R.id.autonomous_own_jewel);
        CheckBox autonomous_other_jewel = (CheckBox) findViewById(R.id.autonomous_other_jewel);

        CheckBox autonomous_safe_zone = (CheckBox) findViewById(R.id.autonomous_safe_zone);

        String[] old_data_array1 = push(old_data_array0, String.valueOf(toInt(autonomous_balanced_checkbox.isChecked())));
        String[] old_data_array2 = push(old_data_array1, String.valueOf(toInt(autonomous_own_jewel.isChecked())));
        String[] old_data_array3 = push(old_data_array2, String.valueOf(toInt(autonomous_other_jewel.isChecked())));
        String[] old_data_array4 = push(old_data_array3, String.valueOf(autonomousGlyphs));
        String[] old_data_array5 = push(old_data_array3, String.valueOf(toInt(autonomous_key_bonus.isChecked())));
        String[] data_array = push(old_data_array4, String.valueOf(toInt(autonomous_safe_zone.isChecked())));

        Intent intent = new Intent(this, TeleOpActivity.class);
        String[] message_array = {String.valueOf(match_number), String.valueOf(team_number), String.valueOf(autonomous_balanced_checkbox.isChecked()), String.valueOf(autonomousGlyphs), String.valueOf(autonomous_key_bonus.isChecked()), String.valueOf(autonomous_own_jewel.isChecked()), String.valueOf(autonomous_other_jewel.isChecked()), String.valueOf(autonomous_safe_zone.isChecked())};
        intent.putExtra("team_number", team_number);
        intent.putExtra("match_number", String.valueOf(match_number));
        intent.putExtra("array", data_array);
        startActivity(intent);
    }

    public void enableDisableAutonomous (Boolean enabled) {
        Button autonomous_glyphs_up = (Button) findViewById(R.id.autonomous_glyphs_up);
        Button autonomous_glyphs_down = (Button) findViewById(R.id.autonomous_glyphs_down);
        CheckBox autonomous_key_bonus = (CheckBox) findViewById(R.id.autonomous_key_bonus);
        CheckBox autonomous_own_jewel = (CheckBox) findViewById(R.id.autonomous_own_jewel);
        CheckBox autonomous_other_jewel = (CheckBox) findViewById(R.id.autonomous_other_jewel);
        TextView autonomous_glyphs_display = (TextView) findViewById(R.id.autonomous_glyphs_display);
        CheckBox autonomous_safe_zone = (CheckBox) findViewById(R.id.autonomous_safe_zone);

        autonomous_glyphs_up.setEnabled(enabled);
        autonomous_glyphs_down.setEnabled(enabled);
        autonomous_own_jewel.setEnabled(enabled);
        autonomous_other_jewel.setEnabled(enabled);
        autonomous_key_bonus.setEnabled(enabled);
        autonomous_safe_zone.setEnabled(enabled);

        autonomousGlyphs = 0;
        autonomous_glyphs_display.setText(String.valueOf(autonomousGlyphs));
        autonomous_key_bonus.setChecked(false);
        autonomous_own_jewel.setChecked(false);
        autonomous_other_jewel.setChecked(false);
        autonomous_safe_zone.setChecked(false);
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
