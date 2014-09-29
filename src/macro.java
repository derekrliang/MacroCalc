import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;


public class macro {

	private JFrame frame;
	private JTextField txtG;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_5;
	
	private JSlider slider;
	
	private JRadioButton loseWeightbtn;
	
	private float protein_perc, carb_perc, fat_perc = 20f;
	private JTextField textField_6;
	private JTextField textField_7;
	
	private int protein_opt = 1;
	private int fat_slider_old = 20;

	private boolean macro_integrity = true;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					macro window = new macro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public macro() {
		initialize();
	}

	public void calculate()
	{
		// check if all necessary fields are filled
		
		String weight = txtG.getText();
		String maint_cals = textField_1.getText();
		
		float weightf = -1;
		
		try {
			weightf = Float.parseFloat(weight);
			
		} catch (NumberFormatException e)
		{
			
		}
		
		int maint_cals_i = -1;
		try {
			maint_cals_i = Integer.parseInt(maint_cals);
			
		} catch (NumberFormatException e)
		{
			
		}
	
		if (weightf < 0 || maint_cals_i < 0)
		{
			return;
		}
		
		// calculate logic based on valid input
		int target_cals = maint_cals_i;
		
		boolean lose_weight = loseWeightbtn.isSelected();
		
		int deficit_surplus = 0;
		
		try
		{
			deficit_surplus = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e)
		{
			
		}
		
		if (lose_weight)
		{
			target_cals = target_cals - deficit_surplus;
		}
		else
		{
			target_cals = target_cals + deficit_surplus;
		}
		
		textField_2.setText(target_cals + " calories");
		
		float protein_f = weightf;
		
		if (protein_opt == 2)
		{
			float lbm_f = 0;
			try {
				lbm_f = Float.parseFloat(textField_6.getText());
				
			} catch (NumberFormatException e)
			{
				
			}
			protein_f = lbm_f;
		}
		else if (protein_opt == 3)
		{
			float value_f = 0;
			try {
				value_f = Float.parseFloat(textField_7.getText());
				
			} catch (NumberFormatException e)
			{
				
			}
			protein_f = value_f;
		}
			
		protein_perc = weightf * 4 * 100 / target_cals;
	
		textField_4.setText(String.format("%.2f", protein_f) + "g" + " (" + String.format("%.2f", protein_perc) + "%)");
		
		float fat_f = target_cals * fat_perc / 100 / 9;

		textField_5.setText(String.format("%.2f", fat_f) + "g" + " (" + String.format("%.2f", fat_perc) + "%)");
		
		float carb_f = (target_cals - (protein_f * 4) - (fat_f * 9)) / 4;
		carb_perc = carb_f * 4 * 100 / target_cals;
		textField_3.setText(String.format("%.2f", carb_f) + "g" + " (" + String.format("%.2f", carb_perc) + "%)");
		
		slider.setValue((int)fat_perc);
		
		if (protein_perc >= 0 && fat_perc >= 0 && carb_perc >= 0 && protein_perc + fat_perc + carb_perc >= 99.9 && protein_perc + fat_perc + carb_perc <= 100)
		{
			macro_integrity = true;
		}
		else
		{
			macro_integrity = false;
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 533, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][grow][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("Weight (lb)");
		frame.getContentPane().add(lblNewLabel, "flowy,cell 0 1");
		
		txtG = new JTextField();
		txtG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("weight changed");
				calculate();
			}
		});
		txtG.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		frame.getContentPane().add(txtG, "cell 0 1,alignx left");
		txtG.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Maintence Calories (kcal)");
		frame.getContentPane().add(lblNewLabel_1, "cell 0 2");
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("maint_cals changed");
				calculate();
			}
		});
		textField_1.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {

			}
		});
		frame.getContentPane().add(textField_1, "cell 0 3,alignx left");
		textField_1.setColumns(10);
		
		JLabel lblWeightGoal = new JLabel("Weight Goal");
		frame.getContentPane().add(lblWeightGoal, "flowx,cell 0 4,alignx leading");
		
		JPanel panel_11 = new JPanel();
		frame.getContentPane().add(panel_11, "flowx,cell 0 5");
		
		JRadioButton rdbtnLoseWeight = new JRadioButton("Lose");
		panel_11.add(rdbtnLoseWeight);
		rdbtnLoseWeight.setSelected(true);
		loseWeightbtn = rdbtnLoseWeight;
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Gain");
		panel_11.add(rdbtnNewRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnLoseWeight);
		group.add(rdbtnNewRadioButton);
		
		ButtonGroup protein_rb_group = new ButtonGroup();
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "cell 0 6");
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblNewLabel_4 = new JLabel("Protein");
		panel_1.add(lblNewLabel_4, "cell 0 0");
		
		JRadioButton protein_rb_1 = new JRadioButton("1g per 1lb");
		protein_rb_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				protein_opt = 1;
			}
		});
		protein_rb_1.setSelected(true);
		panel_1.add(protein_rb_1, "cell 0 1");
		
		JRadioButton protein_rb_2 = new JRadioButton("1g per 1lbm (lbm < lb)");
		protein_rb_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				protein_opt = 2;
			}
		});
		panel_1.add(protein_rb_2, "cell 0 2");
		
		JLabel lblNewLabel_5 = new JLabel("lbm =");
		panel_1.add(lblNewLabel_5, "flowx,cell 1 2");
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "cell 1 2,alignx left");
		textField_6.setColumns(10);
		
		JRadioButton protein_rb_3 = new JRadioButton("");
		protein_rb_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				protein_opt = 3;
			}
		});
		panel_1.add(protein_rb_3, "flowx,cell 0 3");
		protein_rb_group.add(protein_rb_1);
		protein_rb_group.add(protein_rb_2);
		protein_rb_group.add(protein_rb_3);
		
		textField_7 = new JTextField();
		panel_1.add(textField_7, "cell 0 3");
		textField_7.setColumns(10);
		
		JLabel lblG = new JLabel("g");
		panel_1.add(lblG, "cell 0 3");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "flowx,cell 0 8,alignx left");
		panel.setLayout(new MigLayout("", "[grow][]", "[][][][]"));
		
		JLabel lblTargetCalories = new JLabel("Target Calories");
		panel.add(lblTargetCalories, "flowx,cell 0 0");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2, "cell 0 0,alignx right");
		textField_2.setColumns(10);
		
		JLabel lblProtein = new JLabel("Protein");
		panel.add(lblProtein, "flowx,cell 0 1,alignx right");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel.add(textField_4, "cell 0 1,alignx center");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Carbs");
		panel.add(lblNewLabel_2, "flowx,cell 0 2,alignx right");
		
		JLabel lblNewLabel_3 = new JLabel("Fat");
		panel.add(lblNewLabel_3, "flowx,cell 0 3,alignx right,growy");
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel.add(textField_3, "cell 0 2");
		textField_3.setColumns(10);
		
		textField_3.setEditable(false);
		panel.add(textField_3, "cell 0 2");
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		panel.add(textField_5, "cell 0 3");
		textField_5.setColumns(10);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fat_perc = slider.getValue();
				calculate();
				
				if (!macro_integrity)
				{
					System.out.println("integrity violation: fatp = " + fat_perc + " proteinp = " + protein_perc + " carbp = " + carb_perc);
					fat_perc = fat_slider_old;
					calculate();
					System.out.println("revert to: fatp = " + fat_perc + " proteinp = " + protein_perc + " carbp = " + carb_perc);
				}
				else
				{
					fat_slider_old = (int)fat_perc;
				}
			}
		});
		panel.add(slider, "cell 1 3");
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "cell 0 7");
		
		JButton btnCalculate = new JButton("Calculate");
		panel_2.add(btnCalculate);
		
		textField = new JTextField();
		textField.setText("300");
		frame.getContentPane().add(textField, "cell 0 5");
		textField.setColumns(10);
		
		JLabel lblCals = new JLabel("cals deficit/surplus");
		frame.getContentPane().add(lblCals, "cell 0 5");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
	}

}
