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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import static java.security.AccessController.getContext;

//import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

	//TORC stuff that i don't understand

	public static WeakReference<MainActivity> instance;


	int team_number = 0;
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
	int teleopCiphers = 0;

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

	ConstraintLayout prematch_layout;
	ConstraintLayout autonomous_layout;
	ConstraintLayout teleop_layout;
	ConstraintLayout comments_layout;
	ConstraintLayout footer_layout;

	EditText prematch_input_scout_name;
	Spinner prematch_select_scout_team;

	Spinner prematch_select_team;
	EditText prematch_input_match;
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

	Button footer_save_reset;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().setTitle("TOWR5291 2017 Scouting"); // set the top title

		//TORC stuff that I will never understand
		instance = new WeakReference<>(this);

		//Get references to buttons & layouts & whatnot
		prematch_layout = (ConstraintLayout) findViewById(R.id.prematch_layout);
		autonomous_layout = (ConstraintLayout) findViewById(R.id.autonomous_layout);
		teleop_layout = (ConstraintLayout) findViewById(R.id.teleop_layout);
		comments_layout = (ConstraintLayout) findViewById(R.id.finalize_layout);
		footer_layout = (ConstraintLayout) findViewById(R.id.footer_layout);

		prematch_input_scout_name = (EditText) findViewById(R.id.prematch_input_scout_name);
		prematch_select_scout_team = (Spinner) findViewById(R.id.prematch_select_scout_team);
		prematch_select_alliance = (Spinner) findViewById(R.id.prematch_select_alliance);

		prematch_select_team = (Spinner) findViewById(R.id.prematch_select_team);
		prematch_input_match = (EditText) findViewById(R.id.prematch_input_match);

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

		footer_save_reset = (Button) findViewById(R.id.footer_save_reset);


		prematch_layout.setVisibility(View.VISIBLE);
		footer_layout.setVisibility(View.VISIBLE);

		setScreen(0);

		enableDisableAutonomous(false);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int height = displayMetrics.heightPixels;
		int width = displayMetrics.widthPixels;

		autonomous_red_jewel.setWidth(31);
		autonomous_blue_jewel.setWidth(width/2);

		// Setting long & short click listeners

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
					updateButtons();
				}
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
				if (teleopCiphers != 2) {
					teleopCiphers++;
				}
				updateButtons();
			}
		});

		teleop_ciphers.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (teleopCiphers != 0) {
					teleopCiphers--;
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
	}

	public void onSave (View view) {
		Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT);
		int[] team_number_array = getResources().getIntArray(R.array.team_numbers);
		String[] team_name_array = getResources().getStringArray(R.array.team_names);

		match_number = Integer.valueOf(prematch_input_match.getText().toString());
//		ArrayAdapter<String> adapterTeam = new ArrayAdapter<String>(
//				this, android.R.layout.simple_spinner_item, getResources().getString(R.array.team_numbers));
		int spinner_position_team = prematch_select_team.getSelectedItemPosition();

		team_number = team_number_array[spinner_position_team];
		team_name = team_name_array[spinner_position_team];

		int ownJewel = 0;
		int otherJewel = 0;

		if (isRed) {
			if (autonomousRedJewel) {
				ownJewel = 1;
			} else {
				ownJewel = 0;
			}
			if (autonomousBlueJewel) {
				otherJewel = 1;
			} else {
				otherJewel = 0;
			}
		}

		if (!isRed) {
			if(autonomousBlueJewel) {
				ownJewel = 1;
			} else {
				ownJewel = 0;
			}
			if (autonomousRedJewel) {
				otherJewel = 1;
			} else {
				otherJewel = 0;
			}
		}


		if (relic1Zone > 1) {zone1s++;}
		if (relic1Zone > 2) {zone2s++;}
		if (relic1Zone > 3) {zone3s++;}
		if (relic1Standing) {standings++;}

		if (relic2Zone > 1) {zone1s++;}
		if (relic2Zone > 2) {zone2s++;}
		if (relic2Zone > 3) {zone3s++;}
		if (relic2Standing) {standings++;}

		int team_number = 0;
		String team_name = "";
		int match_number = 0;
		String scout = "";
		int scout_team = 0;
		boolean isRed = false;
		String comment = "";
		comment = finalize_comment.getText().toString();

		String[] data_array = {toString(match_number), toString(team_number), team_name, scout, toString(scout_team), toString(autonomousBalanced), toString(ownJewel), toString(otherJewel), toString(autonomousGlyphs), toString(autonomousKeys), toString(autonomousSafeZone), toString(teleopGlyphs), toString(teleopRows), toString(teleopColumns), toString(teleopCiphers), toString(zone1s), toString(zone2s), toString(zone2s), toString(standings), toString(teleopBalanced), "", comment};
		Toast.makeText(getApplicationContext(), "Toast1", Toast.LENGTH_SHORT);
		saveFile(getStoragePath(), data_array);




//		saveToCSV(getFilesDir() , data_array);
//		saveFile(getFilesDir(), data_array);
	}

	public File getStoragePath() {
		return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
	}

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

	public int toInt (boolean variable) {
		if (variable) {
			return 1;
		} else {
			return 0;
		}
	}

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

	public void saveFile(File path, String[] data_array) {
//		File matchFile = new File(path, "match" + data_array[0] + "team" + data_array[1] + ".csv");
		File matchFile = new File(path, "scout.csv");
		Toast.makeText(getApplicationContext(), "Toast2", Toast.LENGTH_SHORT);
		if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//		FileWriter matchesWriter = new FileWriter(matchFile, false);
		try {
			Toast.makeText(getApplicationContext(), "tried", Toast.LENGTH_SHORT);
			FileWriter matchesWriter = new FileWriter(matchFile, false);
			matchFile.createNewFile();
			matchesWriter.write(serializeData(zone1s, zone2s, zone3s, standings));
			matchesWriter.close();


		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Caught error thing", Toast.LENGTH_SHORT);
		}
		} else {
			Toast.makeText(getApplicationContext(), "Went to else", Toast.LENGTH_SHORT);
				if (ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//					Log.w(TAG, "Permission failed for file writing.");
				} else {
					ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
				}
			}
	}

	private static final String COMMA = ",";
	public String serializeData(int zone1s, int zone2s, int zone3s, int standings) {
		return    match_number + COMMA
				+ team_number + COMMA
				+ team_name + COMMA
				+ scout + COMMA
				+ scout_team + COMMA
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
				+ comment
		;
	}

//	private void saveToCSV(File directoryParent, String[] data_array) {
//		String dateTime = new SimpleDateFormat("yy-MM-dd").format(new Date());
//		File csvMatches = new File(directoryParent, dateTime + "-matches.csv");
////		File csvComments = new File(directoryParent, dateTime + "-comments.csv");
//		try {
//			if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//				boolean makeMatches = false;
//				boolean makeComments = true;
//				if (!csvMatches.isFile()) {
//					makeMatches = true;
//					if (!csvMatches.createNewFile()) {
//						return;
//					}
//				}
////				if (!csvComments.isFile()) {
////					makeComments = true;
////					if (!csvComments.createNewFile()) {
////						return;
////					}
////				}
//				FileWriter csvMatchesWriter = new FileWriter(csvMatches, true);
////				FileWriter csvCommentsWriter = new FileWriter(csvComments, true);
////				if (makeMatches)
//				csvMatchesWriter.write("match" + match_number + "team" + team_number);
////				if (makeComments)
////					csvCommentsWriter.write("match" + match_number + "team" + team_number);
//				for (MatchData i : Collections.list(data_array)) {
//					csvMatchesWriter.write(i.serializeDataCSV());
//					csvCommentsWriter.write(i.serializeCommentsCSV());
//				}
////				csvCommentsWriter.write(i.serializeCommentsCSV());
////				Log.i(TAG, "Wrote logs! " + csvMatches.getAbsolutePath());
//				Toast.makeText(getContext(), "Created CSVs!", Toast.LENGTH_SHORT);
//				csvMatchesWriter.close();
////				csvCommentsWriter.close();
//
//
//
//
//			} else {
//				if (ActivityCompat.shouldShowRequestPermissionRationale(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
////					Log.w(TAG, "Permission failed for file writing.");
//				} else {
//					ActivityCompat.requestPermissions(getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//				}
//			}
//		} catch (Exception e) {
////			Log.w(TAG, e);
//		}
//	}

	private static String[] push(String[] array, String push) {
		String[] longer = new String[array.length + 1];
		for (int i = 0; i < array.length; i++)
			longer[i] = array[i];
		longer[array.length] = push;
		return longer;
	}

	public void switchToAutonomous (View view) {
		setScreen(0);
	}

	public void switchToTeleOp (View view) {
		setScreen(1);
	}

	public void switchToComments (View view) {
		setScreen(2);
	}

	public void updateButtons () {

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

		if (teleopCiphers != 0) {
			setButtonColor(teleop_ciphers, true);
			teleop_ciphers.setText(getResources().getText(R.string.ciphers) + "\n" + teleopCiphers);
		} else {
			setButtonColor(teleop_ciphers, false);
			teleop_ciphers.setText(getResources().getText(R.string.ciphers) + "\n0");
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
	}

	public void setButtonColor (Button button, boolean onOff) {
		if (!onOff) {
			button.setBackgroundColor(getResources().getColor(R.color.button_off));
		} else {
			button.setBackgroundColor(getResources().getColor(R.color.button_on));
		}
	}

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

	public void setScreen (int screen) {
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
