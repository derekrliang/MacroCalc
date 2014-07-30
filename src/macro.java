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


public class macro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_5;
	
	private JRadioButton loseWeightbtn;

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
		
		String weight = textField.getText();
		String maint_cals = textField_1.getText();
		
		int weight_int = -1;
		
		try {
			weight_int = Integer.parseInt(weight);
			
		} catch (NumberFormatException e)
		{
			
		}
		
		int maint_cals_int = -1;
		try {
			maint_cals_int = Integer.parseInt(maint_cals);
			
		} catch (NumberFormatException e)
		{
			
		}
	
		if (weight_int < 0 || maint_cals_int < 0)
		{
			return;
		}
		
		// calculate logic based on valid input
		int target_cals = maint_cals_int;
		
		boolean lose_weight = loseWeightbtn.isSelected();
		
		if (lose_weight)
		{
			target_cals = target_cals - 300;
		}
		else
		{
			target_cals = target_cals + 300;
		}
		
		textField_2.setText(target_cals + " calories");
		
		int protein_int = weight_int;
		int protein_per = weight_int * 4 * 100 / target_cals;
		textField_4.setText(protein_int + "g" + " (" + protein_per + "%)");
		
		int fat_perc = 20;
		int fat_int = target_cals * fat_perc / 100 / 9;

		textField_5.setText(fat_int + "g" + " (" + fat_perc + "%)");
		
		int carb_int = (target_cals - (protein_int * 4) - (fat_int * 9)) / 4;
		int carb_per = carb_int * 4 * 100 / target_cals;
		textField_3.setText(carb_int + "g" + " (" + carb_per + "%)");
		
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 501, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Weight");
		frame.getContentPane().add(lblNewLabel, "flowy,cell 0 1");
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("weight changed");
				calculate();
			}
		});
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		frame.getContentPane().add(textField, "cell 0 1,alignx left");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Maintence Calories");
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
		frame.getContentPane().add(lblWeightGoal, "cell 0 4");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 0 6,alignx left,growy");
		panel.setLayout(new MigLayout("", "[grow]", "[][][][]"));
		
		JLabel lblTargetCalories = new JLabel("Target Calories");
		panel.add(lblTargetCalories, "flowx,cell 0 0,alignx right");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2, "cell 0 0");
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
		
		JPanel panel_11 = new JPanel();
		frame.getContentPane().add(panel_11, "cell 0 5");
		
		JRadioButton rdbtnLoseWeight = new JRadioButton("Lose");
		panel_11.add(rdbtnLoseWeight);
		rdbtnLoseWeight.setSelected(true);
		loseWeightbtn = rdbtnLoseWeight;
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Gain");
		panel_11.add(rdbtnNewRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnLoseWeight);
		group.add(rdbtnNewRadioButton);
	}

}
