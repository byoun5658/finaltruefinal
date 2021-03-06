package com.example.yijun.finaltruefinal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
public class chemistry extends Activity {

    private String[] array = {"While traveling, an airplane passenger placed a bag of potato chips in his checked luggage. When he opened the luggage, the bag had exploded and potato chips were everywhere in his luggage. Which gas law is this an example of?",
            "Consider the following compound: CH4, CH3CH3, CH3CH2Cl and CH3CH2OH, which one has the highest boiling point?",
            "To increase solubility of a gas into a liquid the most, then______.",
            "In a liquid, the energy required to increase the surface of the area by a unit amount is called___"};
    private int questionnumber = 0;
    private String[] choicearray = {"Avogadro's Law", "Ideal Gas Law", "Charles's Law", "Boyle's Law",
            "CH4", "CH3CH3", "CH3CH2Cl", "CH3CH2OH",
            "decrease the temperature and lower the pressure",
            "increase the temperature and raise the pressure",
            "decrease the temperature and raise the pressure",
            "increase the temperature and lower the pressure",
            "dipole-dipole force",
            "surface tension",
            "hydrogen bonding",
            "viscosity"};
    private char[] correctanswer = {'D','D','C','B'};
    private char[] youranswer = new char[array.length];
    private String toprinted;
    //above is the resoure used in coding, below is the items from layout//
    TextView chemquestion;
    TextView printed;
    RadioGroup choices;
    RadioButton choicea;
    RadioButton choiceb;
    RadioButton choicec;
    RadioButton choiced;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chemistry);
        //below is to set the group//
        printed = (TextView) findViewById(R.id.textView5);
        choices = (RadioGroup) findViewById(R.id.choices);
        choices.setOnCheckedChangeListener(listen);
        //below is the question and choices set//
        chemquestion = findViewById(R.id.question);
        chemquestion.setText(array[questionnumber]);
        choicea = findViewById(R.id.choicea);
        choicea.setText(choicearray[4 * questionnumber]);
        choiceb = findViewById(R.id.choiceb);
        choiceb.setText(choicearray[4 * questionnumber + 1]);
        choicec = findViewById(R.id.choicec);
        choicec.setText(choicearray[4 * questionnumber + 2]);
        choiced = findViewById(R.id.choiced);
        choiced.setText(choicearray[4 * questionnumber + 3]);
        //below is to set the next button//
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V) {
                if (questionnumber >= array.length - 1) {
                    toprinted = "Your get " + getgrade() + " questions correct";
                    printed.setText(toprinted);
                    return;
                }
                questionnumber++;
                chemquestion.setText(array[questionnumber]);
                choicea.setText(choicearray[4 * questionnumber]);
                choiceb.setText(choicearray[4 * questionnumber + 1]);
                choicec.setText(choicearray[4 * questionnumber + 2]);
                choiced.setText(choicearray[4 * questionnumber + 3]);
                choices.clearCheck();

            }
        });
    }
    private RadioGroup.OnCheckedChangeListener listen=new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id=	group.getCheckedRadioButtonId();
            switch (group.getCheckedRadioButtonId()) {
                case R.id.choicea:
                    youranswer[questionnumber] = 'A';
                    break;
                case R.id.choiceb:
                    youranswer[questionnumber] = 'B';
                    break;
                case R.id.choicec:
                    youranswer[questionnumber] = 'C';
                    break;
                case R.id.choiced:
                    youranswer[questionnumber] = 'D';
                    break;
            }
        }
    };
    public int getgrade() {
        int correct = 0;
        for (int i = 0; i < array.length; i++) {
            if (correctanswer[i] == youranswer[i]) {
                correct++;
            }
        }
        return correct;
    }
}
