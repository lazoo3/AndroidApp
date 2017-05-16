package edu.rit.axv3012.spacekid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class Divide extends Activity implements OnClickListener {
	private int points = 0;
	private int num1 = 0;
	private int num2 = 0;
	private int num3 = 0;
	private int num4 = 0;
	private int num5 = 0;
	private int res = 0;
	private int questions = 0;
	Random r;
	private String checker, resCheck;
	TextView txtQuestion, txtPoint;
	RadioButton radioAns1, radioAns2, radioAns3, radioAns4;
	ImageButton imgBtnNext;
	ArrayList<Integer> rnd = new ArrayList<Integer>();
	AlertDialog.Builder startDialogBuilder;
	final Context context = this;
	String name;

	DatabaseConnector db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addition_layout);
		db = new DatabaseConnector(this);
		txtQuestion = (TextView) findViewById(R.id.txtWelcome);
		radioAns1 = (RadioButton) findViewById(R.id.rAns1);
		radioAns2 = (RadioButton) findViewById(R.id.rAns2);
		radioAns3 = (RadioButton) findViewById(R.id.rAns3);
		radioAns4 = (RadioButton) findViewById(R.id.rAns4);
		r = new Random();
		imgBtnNext = (ImageButton) findViewById(R.id.imgBtnNext);
		txtPoint = (TextView) findViewById(R.id.txtPoint);
		imgBtnNext.setOnClickListener(this);
		question();

	}

	public void question() {

		num2 = r.nextInt(100) + 1;
		num1 = r.nextInt(101);
		// num2 = r.nextInt(101);
		num3 = r.nextInt(70);
		num4 = r.nextInt(101);
		num5 = r.nextInt(35);
		while ((num1 % num2) != 0 && num1 != 0 && num1 != num2) {
			num1 = r.nextInt(101);
			num2 = r.nextInt(100) + 1;
		}
		res = num1 / num2;

		rnd.add(num3);
		rnd.add(num4);
		rnd.add(num5);
		rnd.add(res);

		Collections.shuffle(rnd);

		Integer ans1 = rnd.get(0);
		Integer ans2 = rnd.get(1);
		Integer ans3 = rnd.get(2);
		Integer ans4 = rnd.get(3);

		txtQuestion.setText("How much is " + num1 + " / " + num2 + "?");

		radioAns1.setText(ans1 + "");
		radioAns2.setText(ans2 + "");
		radioAns3.setText(ans3 + "");
		radioAns4.setText(ans4 + "");
		rnd.clear();
	}

	@Override
	public void onClick(View v) {

		if (radioAns1.isChecked()) {
			checker = radioAns1.getText().toString();
			resCheck = Integer.toString(res);
			if (resCheck.equals(checker)) {
				points += r.nextInt(31);
				txtPoint.setText("Points: " + points + "");
			} else {
				points -= 10;
				txtPoint.setText("Points: " + points + "");
			}
		} else if (radioAns2.isChecked()) {
			checker = radioAns2.getText().toString();
			resCheck = Integer.toString(res);
			if (resCheck.equals(checker)) {
				points += r.nextInt(31);
				txtPoint.setText("Points: " + points + "");
			} else {
				points -= 10;
				txtPoint.setText("Points: " + points + "");
			}
		} else if (radioAns3.isChecked()) {
			checker = radioAns3.getText().toString();
			resCheck = Integer.toString(res);
			if (resCheck.equals(checker)) {
				points += r.nextInt(31);
				txtPoint.setText("Points: " + points + "");
			} else {
				points -= 10;
				txtPoint.setText("Points: " + points + "");
			}
		} else if (radioAns4.isChecked()) {
			checker = radioAns4.getText().toString();
			resCheck = Integer.toString(res);
			if (resCheck.equals(checker)) {
				points += r.nextInt(31);
				txtPoint.setText("Points: " + points + "");
			} else {
				points -= 10;
				txtPoint.setText("Points: " + points + "");
			}
		}
		if (questions == 9) {
			alertStart();
		} else {
			question();
			questions++;
		}

	}

	public void alertStart() {
		startDialogBuilder = new AlertDialog.Builder(context);
		startDialogBuilder.setTitle("Highscore: " + points);
		startDialogBuilder
				.setMessage(
						"Click Yes for New Game or No to return to choose another Game.")

				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								name = getIntent().getStringExtra("text");
								db.addScore(new Score(name, points));
								Intent intent = getIntent();
								finish();
								startActivity(intent);

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						name = getIntent().getStringExtra("text");
						db.addScore(new Score(name, points));
						finish();
					}
				});

		AlertDialog alertDialog1 = startDialogBuilder.create();

		alertDialog1.show();
	}

}
