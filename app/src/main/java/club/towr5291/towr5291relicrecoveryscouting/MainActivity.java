package club.towr5291.towr5291relicrecoveryscouting;

import android.Manifest;
import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static android.widget.Toast.makeText;
import static java.lang.Character.LINE_SEPARATOR;
import static java.security.AccessController.getContext;

//import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

	//TORC stuff that i don't understand

	public static WeakReference<MainActivity> instance;

	// Setup the variables used for tracking the status of everything, used in updating the buttons
	// Also has other global variables that are required, like team_name, and scout
	// This must be only within the class, nothing else

	int team_number = 0;
	boolean isOtherTeam = false;
	String team_name = "";
	int match_number = 0;
	String scout = "";
	int scout_team = 0;
	boolean isRed = false;
	String comment = "";

	boolean autonomousBalanced = false;

	boolean autonomousRedJewel = false;
	boolean autonomousBlueJewel = false;

	boolean ownJewel = false;
	boolean otherJewel = false;

	int autonomousGlyphs = 0;
	int autonomousKeys = 0;

	boolean autonomousSafeZone = false;

	int teleopGlyphs = 0;
	int teleopRows = 0;
	int teleopColumns = 0;
	boolean teleopCiphers = false;

	int relic1Zone = 0;
	boolean relic1Standing = false;

	int relic2Zone = 0;
	boolean relic2Standing = false;

	int zone1s = 0;
	int zone2s = 0;
	int zone3s = 0;
	int standings = 0;

	boolean teleopBalanced = false;

	int currentScreen = 0; // 0 = Autonomous, 1 = TeleOp, 2 = Comments

	// The Declaration of Things
	// Sets up the variables for all of the views (buttons, text, dropdowns) and the layouts (prematch, autonomous, teleop, etc.)
	// This section must be only within the class, nothing else

	ConstraintLayout prematch_layout;
	ConstraintLayout autonomous_layout;
	ConstraintLayout teleop_layout;
	ConstraintLayout comments_layout;
	ConstraintLayout footer_layout;

	EditText prematch_input_scout_name;
	Spinner prematch_select_scout_team;

	Spinner prematch_select_team;
//	EditText prematch_input_match;
	TextView prematch_other_team_static;
	EditText prematch_other_team_input;
	Button prematch_match_number;
	Spinner prematch_select_alliance;

	Button autonomous_balanced;

	Button autonomous_red_jewel;
	Button autonomous_blue_jewel;

	Button autonomous_glyphs;
	Button autonomous_key_bonus;

	Button autonomous_safe_zone;

	Button teleop_glyphs;
	Button teleop_rows;
	Button teleop_columns;
	Button teleop_ciphers;

	Button teleop_relic1_unscoRed;
	Button teleop_relic1_zone1;
	Button teleop_relic1_zone2;
	Button teleop_relic1_zone3;
	Button teleop_relic1_standing;

	Button teleop_relic2_unscoRed;
	Button teleop_relic2_zone1;
	Button teleop_relic2_zone2;
	Button teleop_relic2_zone3;
	Button teleop_relic2_standing;

	Button teleop_balanced;

	EditText finalize_comment;

	Button footer_switch_autonomous;
	Button footer_switch_teleop;
	Button footer_switch_comments;

	Button footer_reset;
	Button footer_save_reset;

	TextView footer_debug;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// This method is run on the creation of the activity (screen) and sets EVERYTHING involving the layout up

		// Required Android stuff, sets the layout to activity_main
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// set the top title so that it is more descriptive
		getSupportActionBar().setTitle("TOWR5291 2017 Scouting");

		//TORC thing that I will never understand
		//Required for the saving to work
		instance = new WeakReference<>(this);

		// Load all of the views & layouts to their respective variables so that they can be used later
		// This section MUST go in the onCreate method
		prematch_layout = (ConstraintLayout) findViewById(R.id.prematch_layout);
		autonomous_layout = (ConstraintLayout) findViewById(R.id.autonomous_layout);
		teleop_layout = (ConstraintLayout) findViewById(R.id.teleop_layout);
		comments_layout = (ConstraintLayout) findViewById(R.id.finalize_layout);
		footer_layout = (ConstraintLayout) findViewById(R.id.footer_layout);

		prematch_input_scout_name = (EditText) findViewById(R.id.prematch_input_scout_name);
		prematch_select_scout_team = (Spinner) findViewById(R.id.prematch_select_scout_team);
		prematch_other_team_static = (TextView) findViewById(R.id.prematch_other_team_static);
		prematch_other_team_input = (EditText) findViewById(R.id.prematch_other_team_input);
		prematch_match_number = (Button) findViewById(R.id.prematch_match_number);
		prematch_select_alliance = (Spinner) findViewById(R.id.prematch_select_alliance);

		prematch_select_team = (Spinner) findViewById(R.id.prematch_select_team);
//		prematch_input_match = (EditText) findViewById(R.id.prematch_input_match);

		autonomous_balanced = (Button) findViewById(R.id.autonomous_robot_balanced);

		autonomous_red_jewel = (Button) findViewById(R.id.autonomous_red_jewel);
		autonomous_blue_jewel = (Button) findViewById(R.id.autonomous_blue_jewel);

		autonomous_glyphs = (Button) findViewById(R.id.autonomous_glyphs);
		autonomous_key_bonus = (Button) findViewById(R.id.autonomous_key_bonus);

		autonomous_safe_zone = (Button) findViewById(R.id.autonomous_safe_zone);

		teleop_glyphs = (Button) findViewById(R.id.teleop_glyphs);
		teleop_rows = (Button) findViewById(R.id.teleop_rows);
		teleop_columns = (Button) findViewById(R.id.teleop_columns);
		teleop_ciphers = (Button) findViewById(R.id.teleop_ciphers);

		teleop_relic1_unscoRed = (Button) findViewById(R.id.teleop_relic1_unscored);
		teleop_relic1_zone1 = (Button) findViewById(R.id.teleop_relic1_zone1);
		teleop_relic1_zone2 = (Button) findViewById(R.id.teleop_relic1_zone2);
		teleop_relic1_zone3 = (Button) findViewById(R.id.teleop_relic1_zone3);
		teleop_relic1_standing = (Button) findViewById(R.id.teleop_relic1_standing);

		teleop_relic2_unscoRed = (Button) findViewById(R.id.teleop_relic2_unscored);
		teleop_relic2_zone1 = (Button) findViewById(R.id.teleop_relic2_zone1);
		teleop_relic2_zone2 = (Button) findViewById(R.id.teleop_relic2_zone2);
		teleop_relic2_zone3 = (Button) findViewById(R.id.teleop_relic2_zone3);
		teleop_relic2_standing = (Button) findViewById(R.id.teleop_relic2_standing);

		teleop_balanced = (Button) findViewById(R.id.teleop_balanced);

		finalize_comment = (EditText) findViewById(R.id.finalize_comment);

		footer_switch_autonomous = (Button) findViewById(R.id.footer_switch_autonomous);
		footer_switch_teleop = (Button) findViewById(R.id.footer_switch_teleop);
		footer_switch_comments = (Button) findViewById(R.id.footer_switch_comments);

		footer_reset = (Button) findViewById(R.id.footer_reset);
		footer_save_reset = (Button) findViewById(R.id.footer_save_reset);
//		footer_debug = (TextView) findViewById(R.id.footer_debug);

		prematch_layout.setVisibility(View.VISIBLE);
		footer_layout.setVisibility(View.VISIBLE);


		setScreen(0);

		enableDisableAutonomous(false);

//		prematch_other_team_input.setVisibility(View.GONE);
//		prematch_other_team_static.setVisibility(View.GONE);

		//set listeners for buttons

		setListeners();

		enableOtherTeam(false);

		// I hate these next few lines of code

		// Newer versions of android require that some permissions be requested in the app, and not on install
		// Checks if the saving permissions are granted
		// If there is no permission, seemingly if it is a newer version of android, it pops up a blank
		// Since I am installing the app, I will always hit accept
		int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
				Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (permissionCheck == PackageManager.PERMISSION_GRANTED) {}
		else {
			ActivityCompat.requestPermissions(getContext(),
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					1);
		}
	}

	// Sets up the listeners
	public void setListeners () {
		/*
		Setting up the listeners
		This creates the actions to happen when we want to have a user action be found
		Most of these are for buttons with a short and long click listener, but there a few exceptions
		The save & reset and the reset buttons have only a long listener to detect when the user is sure they want to do it
		At the top, there is a listener for the prematch_select_team which listens for when the users selects something, then if it is other, it pops up the 'other' box
		The all of the listeners, excluding the team dropdown, check if the value should be changed, change it if needed, then updateButtons()

		This method MUST be run at startup
		 */

		prematch_select_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(prematch_select_team.getSelectedItemPosition() == getResources().getStringArray(R.array.team_name_number).length - 1) {
					enableOtherTeam(true);
				} else {
					enableOtherTeam(false);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		prematch_match_number.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				match_number++;
				updateButtons();

			}
		});

		prematch_match_number.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (match_number != 0) {
					match_number--;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_balanced.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousBalanced) {
					autonomousBalanced = true;
					updateButtons();
					enableDisableAutonomous(true);
				}
			}
		});

		autonomous_balanced.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousBalanced) {
					autonomousBalanced = false;
					enableDisableAutonomous(false);
				}
				updateButtons();
				return true;
			}
		});

		autonomous_red_jewel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousRedJewel) {
					autonomousRedJewel = true;
				}
				updateButtons();
			}
		});

		autonomous_red_jewel.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousRedJewel) {
					autonomousRedJewel = false;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_blue_jewel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousBlueJewel) {
					autonomousBlueJewel = true;
					updateButtons();
				}
			}
		});

		autonomous_blue_jewel.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousBlueJewel) {
					autonomousBlueJewel = false;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_glyphs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				autonomousGlyphs++;
				updateButtons();

			}
		});

		autonomous_glyphs.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousGlyphs != 0) {
					autonomousGlyphs--;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_key_bonus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (autonomousKeys != 2) {
					autonomousKeys++;
				}
				updateButtons();

			}
		});

		autonomous_key_bonus.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousKeys != 0) {
					autonomousKeys--;
				}
				updateButtons();
				return true;
			}
		});

		autonomous_safe_zone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!autonomousSafeZone) {
					autonomousSafeZone = true;
					updateButtons();
				}
			}
		});

		autonomous_safe_zone.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (autonomousSafeZone) {
					autonomousSafeZone = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_glyphs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				teleopGlyphs++;
				updateButtons();
			}
		});

		teleop_glyphs.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopGlyphs != 0) {
					teleopGlyphs--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_rows.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (teleopRows != 8) {
					teleopRows++;
				}
				updateButtons();
			}
		});

		teleop_rows.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopRows != 0) {
					teleopRows--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_columns.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (teleopColumns != 6) {
					teleopColumns++;
				}
				updateButtons();
			}
		});

		teleop_columns.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopColumns != 0) {
					teleopColumns--;
				}
				updateButtons();
				return true;
			}
		});

		teleop_ciphers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!teleopCiphers) {
					teleopCiphers = true;
				}
				updateButtons();
			}
		});

		teleop_ciphers.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopCiphers) {
					teleopCiphers = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_relic1_unscoRed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 0;
				updateButtons();
			}
		});

		teleop_relic1_zone1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 1;
				updateButtons();
			}
		});

		teleop_relic1_zone2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 2;
				updateButtons();
			}
		});

		teleop_relic1_zone3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic1Zone = 3;
				updateButtons();
			}
		});

		teleop_relic2_unscoRed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 0;
				updateButtons();
			}
		});

		teleop_relic2_zone1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 1;
				updateButtons();
			}
		});

		teleop_relic2_zone2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 2;
				updateButtons();
			}
		});

		teleop_relic2_zone3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				relic2Zone = 3;
				updateButtons();
			}
		});

		teleop_relic1_standing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!relic1Standing) {
					relic1Standing = true;
					updateButtons();
				}
			}
		});

		teleop_relic1_standing.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (relic1Standing) {
					relic1Standing = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_relic2_standing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!relic2Standing) {
					relic2Standing = true;
					updateButtons();
				}
			}
		});

		teleop_relic2_standing.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (relic2Standing) {
					relic2Standing = false;
				}
				updateButtons();
				return true;
			}
		});

		teleop_balanced.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!teleopBalanced) {
					teleopBalanced = true;
					updateButtons();
				}
			}
		});

		teleop_balanced.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopBalanced) {
					teleopBalanced = false;
				}
				updateButtons();
				return true;
			}
		});

		footer_save_reset.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				onSave();
				updateButtons();
				return true;
			}

		});

		footer_reset.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				resetData();
				updateButtons();
				return true;
			}

		});


	}

	// What happens when the data should be saved
	public void onSave () {

		// Checks if everything is filled out correctly at the top of the screen
		// Gathers all of the data
		// Saves the data

		if (/*prematch_input_match.getText().length() > 0 && */ prematch_input_scout_name.getText().length() >= 4 && ((prematch_select_team.getSelectedItemPosition() != 0 && !isOtherTeam) || (isOtherTeam && prematch_other_team_input.getText().length() > 3)) && prematch_select_alliance.getSelectedItemPosition() != 0 && prematch_select_scout_team.getSelectedItemPosition() != 0 && match_number != 0) {

			int[] team_number_array = getResources().getIntArray(R.array.team_numbers);
			String[] team_name_array = getResources().getStringArray(R.array.team_names);

			int spinner_position_team = prematch_select_team.getSelectedItemPosition();

			if (!isOtherTeam) {
				team_number = team_number_array[spinner_position_team];
				team_name = team_name_array[spinner_position_team];
			} else {
				team_number = toInt(prematch_other_team_input.getText().toString());
				team_name = "Unknown";
			}


			ownJewel = false;
			otherJewel = false;

			if (prematch_select_alliance.getSelectedItemPosition() == 1) {
				isRed = true;
			} else {
				isRed = false;
			}

			if (isRed) {
				if (autonomousRedJewel) {
					ownJewel = true;
				} else {
					ownJewel = false;
				}
				if (autonomousBlueJewel) {
					otherJewel = true;
				} else {
					otherJewel = false;
				}
			}

			if (!isRed) {
				if (autonomousBlueJewel) {
					ownJewel = true;
				} else {
					ownJewel = false;
				}
				if (autonomousRedJewel) {
					otherJewel = true;
				} else {
					otherJewel = false;
				}
			}


			if (relic1Zone >= 1) {
				zone1s++;
			}
			if (relic1Zone >= 2) {
				zone2s++;
			}
			if (relic1Zone >= 3) {
				zone3s++;
			}
			if (relic1Standing) {
				standings++;
			}

			if (relic2Zone >= 1) {
				zone1s++;
			}
			if (relic2Zone >= 2) {
				zone2s++;
			}
			if (relic2Zone >= 3) {
				zone3s++;
			}
			if (relic2Standing) {
				standings++;
			}

			scout = prematch_input_scout_name.getText().toString();

			int scout_team_spinner_position = prematch_select_scout_team.getSelectedItemPosition();
			int[] scout_teams = getResources().getIntArray(R.array.scout_team_number);
			scout_team = scout_teams[scout_team_spinner_position];

//			comment = finalize_comment.getText().toString();
			comment = "";
			String rawComment = finalize_comment.getText().toString();
			for (int i = 0; i < rawComment.length(); i++) {
				if (toString(rawComment.charAt(i)) == COMMA) {
					comment += ".";
				} else if (toString(rawComment.charAt(i)) == ENTER) {
					comment += " ";
				} else {
					comment += toString(rawComment.charAt(i));
				}
				if (toString(rawComment.charAt(i)) == ENTER) {
					Toast toast = makeText(getApplicationContext(), "Contains ENTER", Toast.LENGTH_SHORT);
					toast.show();
				}
			}

			String[] data_array = {toString(match_number), toString(team_number), team_name, scout, toString(scout_team), toString(autonomousBalanced), toString(ownJewel), toString(otherJewel), toString(autonomousGlyphs), toString(autonomousKeys), toString(autonomousSafeZone), toString(teleopGlyphs), toString(teleopRows), toString(teleopColumns), toString(teleopCiphers), toString(zone1s), toString(zone2s), toString(zone2s), toString(standings), toString(teleopBalanced), "", comment};
//		Toast.makeText(getApplicationContext(), "Toast1", Toast.LENGTH_SHORT);


			saveFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), data_array);

		resetData();
		} else {
			Toast toast = makeText(getApplicationContext(), "Please Update the Required Fields to Contain Data", Toast.LENGTH_LONG);
			toast.show();
		}

//		footer_debug.setText(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY)));


//		saveToCSV(getFilesDir() , data_array);
//		saveFile(getFilesDir(), data_array);
	}

	// Resets all of the data for the match, along with the team number, and increments the match number
	public void resetData() {

		// This just resets all of the variables, and makes sure everything is setup as it should be on app startup
		// Then runs updateButtons to update it all

		prematch_select_team.setSelection(0);

		prematch_layout.setVisibility(View.VISIBLE);
		setScreen(0);
		footer_layout.setVisibility(View.VISIBLE);

		team_number = 0;
		team_name = "";
		scout = "";
		scout_team = 0;
		isRed = false;
		comment = "";

		autonomousBalanced = false;

		autonomousRedJewel = false;
		autonomousBlueJewel = false;

		ownJewel = false;
		otherJewel = false;

		autonomousGlyphs = 0;
		autonomousKeys = 0;

		autonomousSafeZone = false;

		teleopGlyphs = 0;
		teleopRows = 0;
		teleopColumns = 0;
		teleopCiphers = false;

		relic1Zone = 0;
		relic1Standing = false;

		relic2Zone = 0;
		relic2Standing = false;

		zone1s = 0;
		zone2s = 0;
		zone3s = 0;
		standings = 0;

		teleopBalanced = false;

		finalize_comment.setText("");

		currentScreen = 0; // 0 = Autonomous, 1 = TeleOp, 2 = Comments

		match_number++;

		enableDisableAutonomous(false);

		updateButtons();

		setScreen(0);

		enableOtherTeam(false);
	}

	// Gets the desired storage path, currently set to the Downloads folder
	public File getStoragePath() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	}

	// A bunch of functions to unclutter the code, just changes things to strings and ints for easy saving and processing
	public String toString (boolean variable) {
		if (variable) {
			return "1";
		} else {
			return "0";
		}
	}
	public String toString (int variable) {
		return String.valueOf(variable);
	}
	public String toString (char variable) {
		return String.valueOf(variable);
	}
	public int toInt (boolean variable) {
		if (variable) {
			return 1;
		} else {
			return 0;
		}
	}
	public int toInt (String variable) {
		return Integer.valueOf(variable);
	}
	public int toInt (char variable) {
		return Integer.valueOf(variable);
	}

	// Not to be confused with getApplicationContext(), this returns an instance of the screen
	private static Activity getContext() throws NullPointerException {
//		if (DebugActivity.instance == null) {
//			if (MainActivity.instance == null) {
//				throw new NullPointerException("Neither DebugActivity nor MainActivity has an instance!");
//			} else {
//				return MainActivity.instance.get();
//			}
//		} else {
//			return DebugActivity.instance.get();
//		}
		return MainActivity.instance.get();
	}

	// The save method, it saves the actual file with the supplied data
	public void saveFile(File path, String[] data_array) {

		// A lot of this function is from the internet

		// Creates a file that is the scout.csv file
//		File matchFile = new File(path, "match" + data_array[0] + "team" + data_array[1] + ".csv");
		File matchFile = new File(path, "scout.csv");

		// Gets the enter to be used in the CSV file
		String ENTER = System.getProperty("line.separator");

		// Creates the way to actually write the files
		FileOutputStream fos;

		// Gets the file path to the csv
		String newPath = path + "/scout.csv";

		// The actual file saving is withing the try's, and the error detection/reporting is within the catch's.
		// If there is an error, it shows a popup with the error and where it was located
		// Everything in the try's must be within there to compile
		try {

			fos = new FileOutputStream(newPath, true); // Sets the FileOutputStream's file path, and sets it to add to the file, not replace

			FileWriter fileWriter; // Creates the file writing process

			try {
				String data = serializeData(zone1s,zone2s,zone3s,standings); // Gets the string to be saved, with commas
				fileWriter = new FileWriter(fos.getFD()); // Finalizes the setup of the FileWriter
				fileWriter.write(data); // Writes the data
				fileWriter.write(ENTER); // Adds an enter so that in the spreadsheet, the data is on a new row
				fileWriter.close(); // Closes the FileWriter (This MUST be done to prevent an error when saving the second time
			} catch (Exception e) {
				Toast toast = makeText(getApplicationContext(), "Caught error: " + e + " within second try", Toast.LENGTH_LONG); // Shows popup with error message
				toast.show();
			} finally {
				fos.getFD().sync(); // idk what this does
				fos.close(); // Closes the output stream, this must be done to prevent errors
				Toast toast = makeText(getApplicationContext(), "Completed File Save (Hopefully)", Toast.LENGTH_SHORT); // Shows save completed dialog
				toast.show();
			}
		} catch (Exception e) {
			Toast toast = makeText(getApplicationContext(), "Caught Error: " + e + " within first try", Toast.LENGTH_LONG); // Shows popup with error message
			toast.show();
		}

	}

	// 2 Characters used to make life easier in the serializeData function and saveFile function
	private static final String COMMA = ",";
	private static final String ENTER = System.getProperty("line.separator");

	// Puts all of the data into one long string, which is then saved to the file.
	// This just makes everything easier and cleaner
	public String serializeData(int zone1s, int zone2s, int zone3s, int standings) {
		return    match_number + COMMA
				+ team_number + COMMA
				+ team_name + COMMA
				+ scout + COMMA
				+ toString(scout_team) + COMMA
				+ toString(autonomousBalanced) + COMMA
				+ toString(ownJewel) + COMMA
				+ toString(otherJewel) + COMMA
				+ toString(autonomousGlyphs) + COMMA
				+ toString(autonomousKeys) + COMMA
				+ toString(autonomousSafeZone) + COMMA
				+ toString(teleopGlyphs) + COMMA
				+ toString(teleopRows) + COMMA
				+ toString(teleopColumns) + COMMA
				+ toString(teleopCiphers) + COMMA
				+ toString(zone1s) + COMMA
				+ toString(zone2s) + COMMA
				+ toString(zone3s) + COMMA
				+ toString(standings) + COMMA
				+ toString(teleopBalanced) + COMMA
				+ "" + COMMA
				+ comment + COMMA
		;
	}


	// The screen switches, run when the button for the switch is presses
	public void switchToAutonomous (View view) {
		setScreen(0);
	}
	public void switchToTeleOp (View view) {
		setScreen(1);
	}
	public void switchToComments (View view) {
		setScreen(2);
	}

	// Updates all of the buttons to reflect their current values
	public void updateButtons () {

		// For integer values, checks if the value is zero, then colors the button 'off' if it is zero, and on if it is not zero, then adds the number to the button text, using string resources
		// For Booleans, just sets the button color accordingly
		// For relic zones, sets the button to be off unless it is the current zone, NOT just that zone or above

		// Note: often times, this method is run multiple times on one user action, but that doesn't affect performance noticeably

		setButtonColor(autonomous_balanced, autonomousBalanced);

		setButtonColor(autonomous_red_jewel, autonomousRedJewel);
		setButtonColor(autonomous_blue_jewel, autonomousBlueJewel);

		if (autonomousGlyphs != 0) {
			setButtonColor(autonomous_glyphs, true);
			autonomous_glyphs.setText(getResources().getText(R.string.glyphs) + "\n" + autonomousGlyphs);
		} else {
			setButtonColor(autonomous_glyphs, false);
			autonomous_glyphs.setText(getResources().getText(R.string.glyphs) + "\n0");
		}

		if (autonomousKeys != 0) {
			setButtonColor(autonomous_key_bonus, true);
			autonomous_key_bonus.setText(getResources().getText(R.string.autonomous_key_bonus) + "\n" + autonomousKeys);
		} else {
			setButtonColor(autonomous_key_bonus, false);
			autonomous_key_bonus.setText(getResources().getText(R.string.autonomous_key_bonus) + "\n" + autonomousKeys);
		}

		setButtonColor(autonomous_safe_zone, autonomousSafeZone);

		if (teleopGlyphs != 0) {
			setButtonColor(teleop_glyphs, true);
			teleop_glyphs.setText(getResources().getText(R.string.glyphs) + "\n" + teleopGlyphs);
		} else {
			setButtonColor(teleop_glyphs, false);
			teleop_glyphs.setText(getResources().getText(R.string.glyphs) + "\n0");
		}

		if (teleopRows != 0) {
			setButtonColor(teleop_rows, true);
			teleop_rows.setText(getResources().getText(R.string.rows) + "\n" + teleopRows);
		} else {
			setButtonColor(teleop_rows, false);
			teleop_rows.setText(getResources().getText(R.string.rows) + "\n0");
		}

		if (teleopColumns != 0) {
			setButtonColor(teleop_columns, true);
			teleop_columns.setText(getResources().getText(R.string.columns) + "\n" + teleopColumns);
		} else {
			setButtonColor(teleop_columns, false);
			teleop_columns.setText(getResources().getText(R.string.columns) + "\n0");
		}

		if (teleopCiphers) {
			setButtonColor(teleop_ciphers, true);
		} else {
			setButtonColor(teleop_ciphers, false);
		}

		Button[] relics1 = {teleop_relic1_unscoRed, teleop_relic1_zone1, teleop_relic1_zone2, teleop_relic1_zone3};

		for (int i = 0; i < 4; i++) {
			if (i == relic1Zone) {
				setButtonColor(relics1[i], true);
			} else {
				setButtonColor(relics1[i], false);
			}
		}

		setButtonColor(teleop_relic1_standing, relic1Standing);

		Button[] relics2 = {teleop_relic2_unscoRed, teleop_relic2_zone1, teleop_relic2_zone2, teleop_relic2_zone3};

		for (int i = 0; i < 4; i++) {
			if (i == relic2Zone) {
				setButtonColor(relics2[i], true);
			} else {
				setButtonColor(relics2[i], false);
			}
		}

		setButtonColor(teleop_relic2_standing, relic2Standing);

		setButtonColor(teleop_balanced, teleopBalanced);

		prematch_match_number.setText(getResources().getText(R.string.prematch_match) + " " + toString(match_number));
	}

	// Sets the button to green or gray depending if the button is on or off
	public void setButtonColor (Button button, boolean onOff) {
		// Uses color resources to keep the color consistent and easy to change
		if (!onOff) {
			button.setBackgroundColor(getResources().getColor(R.color.button_off));
		} else {
			button.setBackgroundColor(getResources().getColor(R.color.button_on));
		}
	}

	// For team picking, is run if the other team editText should show up
	public void enableOtherTeam(Boolean on) {
		if(on) {
			prematch_other_team_static.setVisibility(View.VISIBLE);
			prematch_other_team_input.setVisibility(View.VISIBLE);
		}  else {
			prematch_other_team_static.setVisibility(View.GONE);
			prematch_other_team_input.setVisibility(View.GONE);
		}
		prematch_other_team_input.setText("");
		isOtherTeam = on;
	}

	// For autonomous, enables or disables the buttons and resets their values if the autonomous button is on or off
	public void enableDisableAutonomous (Boolean enabled) {
		autonomous_red_jewel.setEnabled(enabled);
		autonomous_blue_jewel.setEnabled(enabled);
		autonomous_glyphs.setEnabled(enabled);
		autonomous_key_bonus.setEnabled(enabled);
		autonomous_safe_zone.setEnabled(enabled);

		autonomousRedJewel = false;
		autonomousBlueJewel = false;
		autonomousGlyphs = 0;
		autonomousKeys = 0;
		autonomousSafeZone = false;

		updateButtons();
	}

	// Sets the current screen
	public void setScreen (int screen) {
		// Uses hiding the layout to switch, uses hiding so no loading of fragments is required
		// I will admit, fragment loading is better in organization of everything
		if (screen == 0) {
			autonomous_layout.setVisibility(View.VISIBLE);
			teleop_layout.setVisibility(View.GONE);
			comments_layout.setVisibility(View.GONE);

			footer_switch_autonomous.setVisibility(View.GONE);
			footer_switch_teleop.setVisibility(View.VISIBLE);
			footer_switch_comments.setVisibility(View.VISIBLE);
		} else if (screen == 1) {
			autonomous_layout.setVisibility(View.GONE);
			teleop_layout.setVisibility(View.VISIBLE);
			comments_layout.setVisibility(View.GONE);

			footer_switch_autonomous.setVisibility(View.VISIBLE);
			footer_switch_teleop.setVisibility(View.GONE);
			footer_switch_comments.setVisibility(View.VISIBLE);
		} else if (screen == 2) {
			autonomous_layout.setVisibility(View.GONE);
			teleop_layout.setVisibility(View.GONE);
			comments_layout.setVisibility(View.VISIBLE);

			footer_switch_autonomous.setVisibility(View.VISIBLE);
			footer_switch_teleop.setVisibility(View.VISIBLE);
			footer_switch_comments.setVisibility(View.GONE);
		}
	}
}
