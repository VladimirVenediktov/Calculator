import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Сalculator extends JFrame {

	static String text="";//для хранения введенного выражения

	JButton buttonsNum[];//кнопки с цифрами
	JButton plus, minus, mult, div, sqrt, point, equal, clearAll, del, ce, reverse, sign;//активные кнопки
	JButton mc,mr,ms,mPl,mMin,perc;//пока неактивные
	JEditorPane editor;//окно отображения ввода
	Listener listener;//слушатель

	public Сalculator() {//конструктор класса Calculator
		setTitle("Калькулятор");
		setSize(300, 330);
		setBackground(new Color(214,245,255));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//создание меню
		menu();
		
		//инициализация окна, кнопок и слушателя
		editor = new JEditorPane();
		editor.setText("0");
		editor.setBackground(new Color(214,245,255));
		editor.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		editor.setEditable(false);

		listener = new Listener();
		
		//создание кнопок с цифрами и доб. им слушателя
		createButtonsDigits();
		
		plus = new JButton("+");
		minus = new JButton("-");
		mult = new JButton("*");
		div = new JButton("/");
		sqrt = new JButton("√");
		sign = new JButton("±");
		point = new JButton(".");
		equal = new JButton("=");
		
		Font f = new Font("Segoe UI", Font.BOLD, 10);//шрифт для некоторых кнопок
		
		clearAll = new JButton("C");
		clearAll.setFont(f);
		
		del = new JButton("←");
		
		ce = new JButton("CE");
		ce.setFont(f);
		
		reverse = new JButton("1/x");
		reverse.setFont(f);
		
		//неактивные кнопки:
		mc = new JButton("MC");
		mc.setFont(f);
		
		mr = new JButton("MR");
		mr.setFont(f);
		
		ms = new JButton("MS");
		ms.setFont(f);
		
		mPl = new JButton("M+");
		mPl.setFont(f);
		
		mMin = new JButton("M-");
		mMin.setFont(f);
		
		perc = new JButton("%");
		perc.setFont(f);
		
		//общая компоновка GridBagLayout
		setLayoutGridBagLayout();
		
		//Добавляем слушателей:
		point.addActionListener(listener);
		//для операций
		plus.addActionListener(listener);
		minus.addActionListener(listener);
		mult.addActionListener(listener);
		div.addActionListener(listener);
		sqrt.addActionListener(listener);
		clearAll.addActionListener(listener);
		ce.addActionListener(listener);
		del.addActionListener(listener);
		reverse.addActionListener(listener);
		sign.addActionListener(listener);
		//для "равно"
		equal.addActionListener(listener);
	}
	
	//основные методы:
	//метод для создания меню
	public void menu() {
		JMenuBar menuPanel = new JMenuBar();
		Font f1 = new Font("Segoe UI", Font.PLAIN, 12);
		
		JMenu view = new JMenu("Вид");
		view.setFont(f1);
		JMenu pravka = new JMenu("Правка");
		pravka.setFont(f1);
		JMenu spravka = new JMenu("Справка");
		spravka.setFont(f1);
		
		JMenu aboutProgram = new JMenu("О программе");
		aboutProgram.setFont(f1);
		spravka.add(aboutProgram);
		
		JMenuItem aboutProgram1 = new JMenuItem("Неактивные кнопки");
		aboutProgram1.setFont(f1);
		aboutProgram.add(aboutProgram1);
		
		menuPanel.add(view);
		menuPanel.add(pravka);
		menuPanel.add(spravka);
		setJMenuBar(menuPanel);
		aboutProgram1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==aboutProgram1) {
					JOptionPane.showMessageDialog(null,"Неактивные кнопки: MC, MR, MC, M+, M-,'%'");
				}
			}
		});
	}
	
	//создание кнопок с цифрами и доб. им слушателя
	public void createButtonsDigits() {
		buttonsNum = new JButton[10];
		for(int i=0;i<10;i++) {
			this.buttonsNum[i] = new JButton(Integer.toString(i));
			this.buttonsNum[i].addActionListener(this.listener);
		}	
	}
	
	//метод для задания компоновки GridBagLayout
 	public void setLayoutGridBagLayout() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);	
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//окно ввода
		JPanel pWindow = new JPanel();
		pWindow.setLayout(new GridLayout(1, 1));//для объекта pWindow исп. табл. компоновку GridLayout
		pWindow.add(editor);//доб. объект editor на pWindow
		
		gbc.anchor = GridBagConstraints.NORTH;//выравнивание компонента внутри отведенного для него пространства
		gbc.fill = GridBagConstraints.BOTH;//измен. высоты и ширины, чтобы компонент занимал все отведенное для него пространство
		gbc.gridheight = 1;//количество ячеек, занимаемых добавляемым компонентом
		gbc.gridwidth = 2;
		gbc.gridx = 0;//номер столбца ячейки
		gbc.gridy = 0;//номер строки ячейки
		gbc.insets = new Insets(10, 5, 0, 5);//отступы
		gbc.ipadx = 0;//увеличение размеров компонента на заданное количество пикселов
		gbc.ipady = 20;
		gbc.weightx = 0.0;//выделение пространства для столбцов
		gbc.weighty = 0.0;//выделение пространства для строк
	
		gbl.setConstraints(pWindow, gbc);//установим объект класса GridBagConstraints в Layout Manager
		add(pWindow);
		
		//неактивные М*
		JPanel pM = new JPanel();//неактивные M*
		pM.setLayout(new GridLayout(1, 5, 5, 5));
		pM.add(mc);
		pM.add(mr);
		pM.add(ms);
		pM.add(mPl);
		pM.add(mMin);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 3, 0, 3);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.0;
		gbl.setConstraints(pM, gbc);
		add(pM);
		
		//del,CE,C,±,√
		JPanel pDel = new JPanel();
		pDel.setLayout(new GridLayout(1, 3, 5, 5));
		pDel.add(del);
		pDel.add(ce);
		pDel.add(clearAll);
		pDel.add(sign);
		pDel.add(sqrt);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbl.setConstraints(pDel, gbc);
		add(pDel);
				
				//отдельная компоновка GridBagLayout панели цифр
				JPanel pNum = new JPanel();
				pNum.setLayout(gbl);
				
				gbc.fill = GridBagConstraints.BOTH;
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.gridwidth = 1;
				gbl.setConstraints(buttonsNum[7], gbc);
				pNum.add(buttonsNum[7]);
				
				gbc.gridx = 1;
				gbc.gridy = 0;
				gbl.setConstraints(buttonsNum[8], gbc);
				pNum.add(buttonsNum[8]);
				
				gbc.gridx = 2;
				gbc.gridy = 0;
				gbl.setConstraints(buttonsNum[9], gbc);
				pNum.add(buttonsNum[9]);
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbl.setConstraints(buttonsNum[4], gbc);
				pNum.add(buttonsNum[4]);
				
				gbc.gridx = 1;
				gbc.gridy = 1;
				gbl.setConstraints(buttonsNum[5], gbc);
				pNum.add(buttonsNum[5]);
				
				gbc.gridx = 2;
				gbc.gridy = 1;
				gbl.setConstraints(buttonsNum[6], gbc);
				pNum.add(buttonsNum[6]);
				
				gbc.gridx = 0;
				gbc.gridy = 2;
				gbl.setConstraints(buttonsNum[1], gbc);
				pNum.add(buttonsNum[1]);
				
				gbc.gridx = 1;
				gbc.gridy = 2;
				gbl.setConstraints(buttonsNum[2], gbc);
				pNum.add(buttonsNum[2]);
				
				gbc.gridx = 2;
				gbc.gridy = 2;
				gbl.setConstraints(buttonsNum[3], gbc);
				pNum.add(buttonsNum[3]);
				
				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.gridwidth = 2;
				gbl.setConstraints(buttonsNum[0], gbc);
				pNum.add(buttonsNum[0]);
				
				gbc.gridx = 2;
				gbc.gridy = 3;
				gbc.gridwidth = 1;
				gbl.setConstraints(point, gbc);
				pNum.add(point);
				//отдельная компоновка панели цифр завершена
				
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(pNum, gbc);
		add(pNum);
		
		
				//отдельная компоновка панели операций
				JPanel pOperation = new JPanel();
				pOperation.setLayout(gbl);
				
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbl.setConstraints(div, gbc);
				pOperation.add(div);
				
				gbc.gridx = 1;
				gbc.gridy = 0;
				gbl.setConstraints(perc, gbc);
				pOperation.add(perc);
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbl.setConstraints(mult, gbc);
				pOperation.add(mult);
				
				gbc.gridx = 1;
				gbc.gridy = 1;
				gbl.setConstraints(reverse, gbc);
				pOperation.add(reverse);
				
				gbc.gridx = 0;
				gbc.gridy = 2;
				gbl.setConstraints(minus, gbc);
				pOperation.add(minus);	
				
				gbc.gridx = 1;
				gbc.gridy = 2;
				gbc.gridheight = 2;
				gbl.setConstraints(equal, gbc);
				pOperation.add(equal);	
				
				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.gridheight = 1;
				gbl.setConstraints(plus, gbc);
				pOperation.add(plus);	
				//отдельная компоновка панели операций завершена
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(pOperation, gbc);
		add(pOperation);
	}
	
	class Listener implements ActionListener{//класс объекта слушатель для кнопок

		@Override
		public void actionPerformed(ActionEvent e) {
			//для цифр и точки:
			for (int i=0;i<10;i++) {
				if(e.getSource()==buttonsNum[i]) {
					addTextNum(Integer.toString(i));//добавляем цифру в выражение
				}
			}
			
			//для точки:
			if(e.getSource()==point) {
				addTextNum(".");
			}
			
			//для операций:
			if(e.getSource()==plus) {
				addTextOper('+');//добавляем знак операции (с пробелами) в выражение
			}
			if(e.getSource()==minus) {
				addTextOper('-');
			}
			if(e.getSource()==mult) {
				addTextOper('*');
			}
			if(e.getSource()==div) {
				addTextOper('/');
			}
			if(e.getSource()==sqrt) {
				addTextOper('√');
			}
			if(e.getSource()==clearAll) {//очистка всего введенного выражения (С)
				editor.setText("0");
				editor.repaint();
				text="";
			}
			if(e.getSource()==del) {//delete
				char textArr[] = new char[text.length()];	
				textArr = text.toCharArray();//преобразуем строку введенного выражения в массив символов
				text="";
				for (int i=0;i<textArr.length-1;i++) {
					text = text + textArr[i];
				}
				editor.setText(text);
				editor.repaint();
			}
			if(e.getSource()==ce) {//удаляем последнее введенное число (CE)
				if (text.indexOf(" ")!=-1) {
					text = text.substring(0,text.indexOf(" ")+3);
					editor.setText(text);
				}
				else {
					text="";
					editor.setText("0");
				}
				editor.repaint();
			}
			if(e.getSource()==reverse) {//вычисление обратного числа
				
				try{
					text = String.valueOf(1.0/Double.parseDouble(editor.getText()));
				}
				catch (NumberFormatException exc) {
					System.out.println("Ошибка ввода операции");
				}
				editor.setText(text);
				editor.repaint();
			}
			if(e.getSource()==sign) {//знак числа
				if (text!="") {
					text = String.valueOf(-Double.parseDouble(text));
					editor.setText(text);
					editor.repaint();
				}
			}
			
			//для "равно":
			if(e.getSource()==equal) {
				char textArr[] = new char[text.length()];	
				textArr = text.toCharArray();
				double x=0;//первое число
				double y=0;//второе число
				int z=0;//индекс знака операции
				for (int i=0;i<textArr.length;i++) {
					if (((textArr[i]=='+')||(textArr[i]=='-')||(textArr[i]=='*')||(textArr[i]=='/'))&& textArr[i+1]==' ') {
						x = Double.parseDouble(text.substring(0, i-1));//извлекаем из строки первое число (с начала и до знака операции)
						y = Double.parseDouble(text.substring(i+2));//извлекаем из строки второе число
						z = i;
						
						switch(text.charAt(z)) {
						case '+':
							text = Double.toString(sum(x,y));
							editor.setText(text); //метод - сложение
							break;
						
						case '-':
							text = Double.toString(dif( x, y));
							editor.setText(text); //метод - вычитание
							break;	
						
						case '*':
							text = Double.toString(mult(x, y));
							editor.setText(text); //метод - умножение
							break;
						
						case '/': // метод - деление
							try{
								text = Double.toString(div(x,y));
								editor.setText(text);
							}
							catch (ArithmeticException exc2) {
								System.out.println("Деление на ноль недопустимо");
								editor.setText("Деление на ноль недопустимо");
								text="";
							}
							break;
						
						default:
							break;
						}
					}

					if (textArr[i]=='√') {//если в выражении вычисляется корень, то извлекаем одно число
						x = Double.parseDouble(text.substring(0,text.indexOf(" √ ")));
						z = i;
						editor.setText(sqrt(x));//метод для вычисления квадр. корня
					}
				}
			}
		
		}
		
		public void addTextNum(String num) {//метод добавляет цифру в окно ввода
			
			text = text+num;
			editor.setText(text);
			editor.repaint();
		}
		
		public void addTextOper(char s) {//метод добавляет символ операции в окно ввода
			
			char textArr[] = new char[text.length()];	
			textArr = text.toCharArray();
			
			//проверяем содержится ли уже какая-нибудь операция в выражении? 
			//если да, то клик по значку любой операции эквивалентен клику по "равно"
			
				if (text.contains("+")||text.contains("-")||text.contains("*")||text.contains("/")||text.contains("√")) {
					//если уже введен символ операции (пробел может быть только после знака операции) 
					//и следом вводится еще одна операция подряд, то меняем старую на новую
					if (textArr[textArr.length-1]==' ') {
						textArr[textArr.length-2] = s;//меняем операцию
						//text = Arrays.toString(textArr);
						text ="";
						for (int i=0;i<textArr.length;i++) {
							
							text = text + textArr[i];//преобразуем массив с замененым знаком операции в строку
						}
						editor.setText(text);
						editor.repaint();
					}
					else {//если какая-либо операция уже была введена (но она не в конце выражения) - ввод новой операции -> клик "равно"
						equal.doClick();
						text = editor.getText()+' '+s+' ';
						editor.setText(text);
						editor.repaint();
					}
				}
				else {
					if (text.length()!=0) {//если какое-то число уже было введено
						text = text+' '+s+' ';//и если никакая операция еще не была введена - просто добавляем символ операции (с пробелами)
						editor.setText(text);
						editor.repaint();	
					}
					else {//никакого выражения введено не было
						text =  "0 "+s+' ';//и если никакая операция еще не была введена - просто добавляем символ операции (с пробелами)
						editor.setText(text);
						editor.repaint();	
					}
				}	
				
		}
	}
	//методы арифметических операций
	
	double sum(double x,double y) {//метод - сложение
		return x+y;
	}
	
	double dif(double x,double y) {//метод - вычитание
		return x-y;
	}
	
	double mult(double x,double y) {//метод - умножение
		return x*y;
	}
	
	double div(double x,double y) throws ArithmeticException {//метод - деление
		if (y==0) {
			throw new ArithmeticException();//деление на ноль
		}
		return x/y;
	}
	
	String sqrt(double x) {//метод - извлечение квадр. корня
		text = String.valueOf(Math.pow(x, 0.5));
		return text;
	}
	
	public static void main(String[] args) {
		new Сalculator().setVisible(true);
	}
}
