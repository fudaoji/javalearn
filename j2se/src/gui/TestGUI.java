package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import jdbc.Db;
import jdbc.Hero;
import jdbc.HeroDAO;

public class TestGUI {

	static HeroTableModel htm = new HeroTableModel(); // 创建一个TableModel

	public static void main(String[] args) {
		ui();
		// swingWorker();
		// test05();
		// tableModel();
		// test04();
		// test03();
		// test02();
		// test01();
		// cardLayout();
		// jFileChooser();
		// textField();
		// jOptionPanel();
		// cal();
		// resizable();
		// jDialog();
		// modalJDialog();
	}

	public static void ui() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
			// handle exception
		}

		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setLayout(null);
		JButton b = new JButton("一键秒对方基地挂");
		b.setBounds(50, 50, 280, 30);

		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);
	}

	public static void swingWorker() {
		JFrame f = new JFrame("LoL");
		f.setSize(300, 300);
		f.setLocation(200, 200);
		f.setLayout(new FlowLayout());

		JButton b1 = new JButton("在事件调度线程中执行长耗时任务");
		JButton b2 = new JButton("使用SwingWorker执行长耗时任务");
		JLabel l = new JLabel("任务执行结果");
		f.add(b1);
		f.add(b2);
		f.add(l);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				l.setText("开始执行任务");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				l.setText("任务执行完毕");
			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						System.out.println("执行这个SwingWorder的线程是：" + Thread.currentThread().getName());
						l.setText("开始执行任务");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						l.setText("任务执行完毕");
						return null;
					}
				};
				worker.execute();

			}
		});

		f.setVisible(true);
	}

	public static void test05() {
		JFrame f = new JFrame("LoL");
		f.setSize(500, 400);
		f.setLocation(200, 200);
		f.setLayout(new BorderLayout());

		// 根据 TableModel来创建 Table
		JTable t = new JTable(htm);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 选中第一行 （基本0）
		t.getSelectionModel().setSelectionInterval(0, 0);

		// 使用滚动面板,表格放到面板上
		JScrollPane sp = new JScrollPane(t);

		// 准备一个Panel上面放一个Label用于显示哪条被选中了
		JPanel p = new JPanel();
		JLabel l = new JLabel("暂时未选中条目");
		p.add(l);

		// 底部Panel新增按钮键
		JPanel bottomP = new JPanel();
		bottomP.setLayout(new FlowLayout());
		JButton addB = new JButton("新增");
		addB.setPreferredSize(new Dimension(80, 30));
		bottomP.add(addB);

		JButton setB = new JButton("编辑");
		setB.setPreferredSize(new Dimension(80, 30));
		bottomP.add(setB);

		JButton delB = new JButton("删除");
		delB.setPreferredSize(new Dimension(80, 30));
		bottomP.add(delB);

		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(bottomP, BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		// 点击编辑
		setB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取哪一行被选中了
				int row = t.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(f, "请先选中要编辑的对象！");
					return;
				}

				Hero h = htm.heros.get(row);

				// 根据外部窗体实例化JDialog
				JDialog d = new JDialog(f);
				d.setTitle("新增英雄");
				d.setSize(400, 300);
				d.setLocation(200, 200);
				d.setLayout(new FlowLayout());

				JButton submitB = new JButton("确定");

				JLabel tlName = new JLabel("名称：");
				JTextField tfName = new JTextField("");
				tfName.setPreferredSize(new Dimension(100, 30));
				JLabel tlHp = new JLabel("血量：");
				JTextField tfHp = new JTextField("");
				tfHp.setPreferredSize(new Dimension(100, 30));

				tfName.setText(h.name);
				tfHp.setText(String.valueOf(h.hp));

				d.add(tlName);
				d.add(tfName);
				d.add(tlHp);
				d.add(tfHp);
				d.add(submitB);

				d.setVisible(true);

				// 监听“新增”按钮的点击事件
				submitB.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name = tfName.getText();

						// 通过name长度判断 名称是否为空
						if (name.length() == 0) {
							// 弹出对话框提示用户
							JOptionPane.showMessageDialog(f, "名称不能为空");

							// 名称输入框获取焦点
							tfName.grabFocus();
							return;
						}

						String hp = tfHp.getText().trim();
						Float rHp = (float) 0;
						try {
							// 把hp转换为浮点型，如果出现异常NumberFormatException表示不是浮点型格式
							rHp = Float.parseFloat(hp);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(f, "血量只能是小数 ");
							tfHp.grabFocus();
							return;
						}

						h.name = name;
						h.hp = rHp;

						HeroDAO dao = new HeroDAO();
						dao.update(h);

						htm.heros = dao.list(); // 通过dao更新tablemodel中的数据
						t.updateUI(); // 更新表格
						JOptionPane.showMessageDialog(f, "更新成功！");
					}
				});
			}
		});

		// 点击删除
		delB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取哪一行被选中了
				int row = t.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(f, "请先选中要删除的对象！");
					return;
				}

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(f, "确定删除此数据吗 ？")) {
					// 根据选中的行，到HeroTableModel中获取对应的对象
					Hero h = htm.heros.get(row);
					HeroDAO dao = new HeroDAO();
					dao.delete(h.id);

					htm.heros = dao.list(); // 通过dao更新tablemodel中的数据
					t.updateUI(); // 更新表格
					JOptionPane.showMessageDialog(f, "删除成功！");
				}
			}
		});

		// 使用selection监听器来监听table的哪个条目被选中
		t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			// 当选择了某一行的时候触发该事件
			public void valueChanged(ListSelectionEvent e) {
				// 获取哪一行被选中了
				int row = t.getSelectedRow();
				// 根据选中的行，到HeroTableModel中获取对应的对象
				Hero h = htm.heros.get(row);
				// 更新标签内容
				l.setText("当前选中的英雄是： " + h.name);

			}
		});

		// 点击新增
		addB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 根据外部窗体实例化JDialog
				JDialog d = new JDialog(f);
				// 设置为模态
				// d.setModal(true);

				d.setTitle("新增英雄");
				d.setSize(400, 300);
				d.setLocation(200, 200);
				d.setLayout(new FlowLayout());

				JButton submitB = new JButton("确定");

				JLabel tlName = new JLabel("名称：");
				JTextField tfName = new JTextField("");
				tfName.setPreferredSize(new Dimension(100, 30));
				JLabel tlHp = new JLabel("血量：");
				JTextField tfHp = new JTextField("");
				tfHp.setPreferredSize(new Dimension(100, 30));

				d.add(tlName);
				d.add(tfName);
				d.add(tlHp);
				d.add(tfHp);
				d.add(submitB);

				d.setVisible(true);

				// 监听“新增”按钮的点击事件
				submitB.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String name = tfName.getText();

						// 通过name长度判断 名称是否为空
						if (name.length() == 0) {
							// 弹出对话框提示用户
							JOptionPane.showMessageDialog(f, "名称不能为空");

							// 名称输入框获取焦点
							tfName.grabFocus();
							return;
						}

						String hp = tfHp.getText().trim();
						Float rHp = (float) 0;
						try {
							// 把hp转换为浮点型，如果出现异常NumberFormatException表示不是浮点型格式
							rHp = Float.parseFloat(hp);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(f, "血量只能是小数 ");
							tfHp.grabFocus();
							return;
						}

						Hero h = new Hero();
						h.name = name;
						h.hp = rHp;

						HeroDAO dao = new HeroDAO();
						dao.add(h);

						htm.heros = dao.list(); // 通过dao更新tablemodel中的数据
						t.updateUI(); // 更新表格
					}
				});
			}

		});

	}

	public static void tableModel() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setLayout(new BorderLayout());

		// 创建一个TableModel
		HeroTableModel htm = new HeroTableModel();
		// 根据 TableModel来创建 Table
		JTable t = new JTable(htm);
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 选中第一行 （基本0）
		t.getSelectionModel().setSelectionInterval(0, 0);

		// 使用滚动面板
		JScrollPane sp = new JScrollPane(t);

		// 准备一个Panel上面放一个Label用于显示哪条被选中了
		JPanel p = new JPanel();
		JLabel l = new JLabel("暂时未选中条目");
		p.add(l);

		// 准备一个Panel上面放一个新增hero的表单
		JPanel bottomP = new JPanel();
		bottomP.setLayout(new FlowLayout());
		JLabel tlName = new JLabel("名称：");
		JTextField tfName = new JTextField("");
		tfName.setPreferredSize(new Dimension(100, 30));
		JLabel tlHp = new JLabel("血量：");
		JTextField tfHp = new JTextField("");
		tfHp.setPreferredSize(new Dimension(100, 30));
		JButton b = new JButton("新增");
		b.setPreferredSize(new Dimension(80, 30));
		bottomP.add(tlName);
		bottomP.add(tfName);
		bottomP.add(tlHp);
		bottomP.add(tfHp);
		bottomP.add(b);

		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(bottomP, BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		// 使用selection监听器来监听table的哪个条目被选中
		t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			// 当选择了某一行的时候触发该事件
			public void valueChanged(ListSelectionEvent e) {
				// 获取哪一行被选中了
				int row = t.getSelectedRow();
				// 根据选中的行，到HeroTableModel中获取对应的对象
				Hero h = htm.heros.get(row);
				// 更新标签内容
				l.setText("当前选中的英雄是： " + h.name);

			}
		});
		// 监听“新增”按钮的点击事件
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = tfName.getText();

				// 通过name长度判断 名称是否为空
				if (name.length() == 0) {
					// 弹出对话框提示用户
					JOptionPane.showMessageDialog(f, "名称不能为空");

					// 名称输入框获取焦点
					tfName.grabFocus();
					return;
				}

				String hp = tfHp.getText().trim();
				Float rHp = (float) 0;
				try {
					// 把hp转换为浮点型，如果出现异常NumberFormatException表示不是浮点型格式
					rHp = Float.parseFloat(hp);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(f, "血量只能是小数 ");
					tfHp.grabFocus();
					return;
				}

				Hero h = new Hero();
				h.name = name;
				h.hp = rHp;

				HeroDAO dao = new HeroDAO();
				dao.add(h);

				htm.heros = dao.list(); // 通过dao更新tablemodel中的数据
				t.updateUI(); // 更新表格
			}
		});
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 设计一个线程，每隔100毫秒，就把进度条的进度+1。 从0%一直加到100%
	 * 刚开始加的比较快，以每隔100毫秒的速度增加，随着进度的增加，越加越慢
	 */
	public static void test04() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setResizable(false);
		f.setLayout(new FlowLayout());

		JProgressBar pb = new JProgressBar();
		// 进度条最大100
		pb.setMaximum(100);
		// 显示当前进度
		pb.setStringPainted(true);
		pb.setPreferredSize(new Dimension(200, 30));
		// 使用线程去跑
		new Thread() {
			public void run() {
				int count = 100;
				while (true) {
					try {
						pb.setValue(pb.getValue() + 1);
						if (pb.getValue() >= 60) {
							count += 100;
						}
						Thread.sleep(count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

		f.add(pb);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	/**
	 * 准备两个JTextFiled,一个用于输入账号，一个用于输入密码。 再准备一个JButton，上面的文字是登陆
	 * 点击按钮之后，首先进行为空判断，如果都不为空，则把账号和密码，拿到数据库中进行比较(SQL语句判断账号密码是否正确)，根据判断结果，
	 * 使用JOptionPane进行提示。
	 */
	public static void test03() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setResizable(false);
		f.setLayout(null);

		JLabel lName = new JLabel("账号：");
		JTextField tfName = new JTextField("");
		tfName.setPreferredSize(new Dimension(80, 30));

		JLabel lPassword = new JLabel("密码：");
		JTextField tfPassword = new JPasswordField("");
		tfPassword.setPreferredSize(new Dimension(80, 30));
		// 设置面板1
		JPanel p1 = new JPanel();
		p1.setBounds(0, 0, 400, 100);
		p1.add(lName);
		p1.add(tfName);
		p1.add(lPassword);
		p1.add(tfPassword);

		JButton loginB = new JButton("登陆");
		// 设置面板2
		JPanel p2 = new JPanel();
		p2.setBounds(0, 101, 400, 100);
		p2.add(loginB);

		f.add(p1);
		f.add(p2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		tfName.grabFocus();

		// 登陆事件
		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfName.getText();
				String password = tfPassword.getText();
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(f, "请输入账号和密码");
				}
				// 连接数据库并判断账号密码
				String sql = "select *  from user where name='" + username + "' and password='" + password + "'";
				Db db = new Db();
				try {
					ResultSet rs = db.select(sql);
					if (rs.next()) {
						/*
						 * ArrayList alRowData = new ArrayList();
						 * ResultSetMetaData rsmd = rs.getMetaData(); int
						 * numberOfColumns = rsmd.getColumnCount(); for(int
						 * columnIndex = 1; columnIndex <= numberOfColumns;
						 * columnIndex ++){
						 * alRowData.add(rs.getObject(columnIndex)); }
						 * System.out.println(alRowData);
						 */

						JOptionPane.showMessageDialog(f, "登录成功");
					} else {
						JOptionPane.showMessageDialog(f, "账号或密码错误");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * 在JTextField中输入数据，在旁边加一个按钮JButton,当点击按钮的时候，判断JTextFiled中有没有数据，
	 * 并使用JOptionPane进行提示
	 */
	public static void test02() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(580, 240);
		f.setLayout(new FlowLayout());

		JTextField tfName = new JTextField("");
		tfName.setPreferredSize(new Dimension(100, 30));

		JButton b = new JButton("检测");
		b.setPreferredSize(new Dimension(80, 30));

		f.add(tfName);
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		b.addActionListener(new ActionListener() {
			// 当按钮被点击时，就会触发 ActionEvent事件
			// actionPerformed 方法就会被执行
			public void actionPerformed(ActionEvent e) {
				String text = tfName.getText();
				if (text.equals("")) {
					JOptionPane.showMessageDialog(f, "输入内容为空");
				} else {
					if (isNumeric(text)) {
						JOptionPane.showMessageDialog(f, "输入是数字，为" + text);
					} else {
						JOptionPane.showMessageDialog(f, "输入是字符串，为" + text);
					}
				}
			}
		});
	}

	/**
	 * 设计一个像SplitPanel的左右风格的SplitPanel
	 */
	public static void test01() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);

		f.setLayout(null);
		// 左panel
		JPanel pLeft = new JPanel();
		pLeft.setBounds(50, 50, 300, 60);
		pLeft.setLayout(new FlowLayout());

		JButton b1 = new JButton("盖伦");
		JButton b2 = new JButton("提莫");
		JButton b3 = new JButton("安妮");

		pLeft.add(b1);
		pLeft.add(b2);
		pLeft.add(b3);

		// 右panel
		JPanel pRight = new JPanel();
		pRight.setBounds(10, 150, 300, 60);

		final JLabel l = new JLabel();
		String[] pics = { "E:/workspace/java/j2se/src/gui/pics/gareen.jpg",
				"E:/workspace/java/j2se/src/gui/pics/teemo.jpg", "E:/workspace/java/j2se/src/gui/pics/annie.jpg" };
		ImageIcon i = new ImageIcon(pics[0]);
		l.setIcon(i);
		l.setBounds(50, 50, i.getIconWidth(), i.getIconHeight());
		pRight.add(l);

		// 创建一个水平JSplitPane，左边是p1,右边是p2
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		// 设置分割条的位置
		sp.setDividerLocation(80);

		// 把sp当作ContentPane
		f.setContentPane(sp);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		b1.addActionListener(new ActionListener() {
			// 当按钮被点击时，就会触发 ActionEvent事件
			// actionPerformed 方法就会被执行
			public void actionPerformed(ActionEvent e) {
				ImageIcon i = new ImageIcon(pics[0]);
				l.setIcon(i);
			}
		});
		b2.addActionListener(new ActionListener() {
			// 当按钮被点击时，就会触发 ActionEvent事件
			// actionPerformed 方法就会被执行
			public void actionPerformed(ActionEvent e) {
				ImageIcon i = new ImageIcon(pics[1]);
				l.setIcon(i);
			}
		});
		b3.addActionListener(new ActionListener() {
			// 当按钮被点击时，就会触发 ActionEvent事件
			// actionPerformed 方法就会被执行
			public void actionPerformed(ActionEvent e) {
				ImageIcon i = new ImageIcon(pics[2]);
				l.setIcon(i);
			}
		});
	}

	public static void cardLayout() {
		JFrame f = new JFrame("CardLayerout");

		// 下拉框
		JPanel comboBoxPane = new JPanel();
		String buttonPanel = "按钮面板";
		String inputPanel = "输入框面板";
		String comboBoxItems[] = { buttonPanel, inputPanel };
		JComboBox<String> cb = new JComboBox<>(comboBoxItems);
		comboBoxPane.add(cb);

		// 两个Panel充当卡片
		JPanel card1 = new JPanel();
		card1.add(new JButton("按钮 1"));
		card1.add(new JButton("按钮 2"));
		card1.add(new JButton("按钮 3"));

		JPanel card2 = new JPanel();
		card2.add(new JTextField("输入框", 20));

		JPanel cards; // a panel that uses CardLayout
		cards = new JPanel(new CardLayout());
		cards.add(card1, buttonPanel); // buttonPanel 控制这个card
		cards.add(card2, inputPanel); // inputPanel 控制这个card

		f.add(comboBoxPane, BorderLayout.NORTH);
		f.add(cards, BorderLayout.CENTER);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 150);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent evt) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, (String) evt.getItem());
			}
		});
	}

	public static void jFileChooser() {
		JFrame f = new JFrame("LOL");
		f.setLayout(new FlowLayout());
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".txt";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".txt");
			}
		});

		JButton bOpen = new JButton("打开文件");

		JButton bSave = new JButton("保存文件");

		f.add(bOpen);
		f.add(bSave);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 150);
		f.setLocationRelativeTo(null);

		f.setVisible(true);

		bOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(f);
				File file = fc.getSelectedFile();
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(f, "计划打开文件:" + file.getAbsolutePath());
				}

			}
		});
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showSaveDialog(f);
				File file = fc.getSelectedFile();
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(f, "计划保存到文件:" + file.getAbsolutePath());
				}
			}
		});
	}

	/**
	 * 文本框
	 */
	public static void textField() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);

		f.setLayout(new FlowLayout());

		JLabel lName = new JLabel("账号：");
		// 输入框
		JTextField tfName = new JTextField("");
		tfName.setText("请输入账号");
		tfName.setPreferredSize(new Dimension(80, 30));

		JLabel lPassword = new JLabel("密码：");
		// 输入框
		JTextField tfPassword = new JPasswordField("");
		tfPassword.setText("请输入密码");
		tfPassword.setPreferredSize(new Dimension(80, 30));

		f.add(lName);
		f.add(tfName);
		f.add(lPassword);
		f.add(tfPassword);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);
		tfPassword.grabFocus();
	}

	public static void jOptionPanel() {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(580, 240);
		f.setLayout(null);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);

		int option = JOptionPane.showConfirmDialog(f, "是否 使用外挂 ？");
		if (JOptionPane.OK_OPTION == option) {
			String answer = JOptionPane.showInputDialog(f, "请输入yes，表明使用外挂后果自负");
			if ("yes".equals(answer))
				JOptionPane.showMessageDialog(f, "你使用外挂被抓住！ 罚拣肥皂3次！");
		}
	}

	public static void cal() {
		JFrame f = new JFrame("计算器");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setResizable(false);
		// 设置布局器为GridLayerout，即网格布局器
		// 该GridLayerout的构造方法表示该网格是2行3列
		f.setLayout(new GridLayout(4, 5, 5, 5));

		JButton b1 = new JButton("7");
		JButton b2 = new JButton("8");
		JButton b3 = new JButton("9");
		JButton b4 = new JButton("/");
		JButton b5 = new JButton("sq");
		JButton b6 = new JButton("4");
		JButton b7 = new JButton("5");
		JButton b8 = new JButton("6");
		JButton b9 = new JButton("*");
		JButton b10 = new JButton("%");
		JButton b11 = new JButton("1");
		JButton b12 = new JButton("2");
		JButton b13 = new JButton("3");
		JButton b14 = new JButton("-");
		JButton b15 = new JButton("1/x");
		JButton b16 = new JButton("0");
		JButton b17 = new JButton("+/-");
		JButton b18 = new JButton(".");
		JButton b19 = new JButton("+");
		JButton b20 = new JButton("=");

		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(b7);
		f.add(b8);
		f.add(b9);
		f.add(b10);
		f.add(b11);
		f.add(b12);
		f.add(b13);
		f.add(b14);
		f.add(b15);
		f.add(b16);
		f.add(b17);
		f.add(b18);
		f.add(b19);
		f.add(b20);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);
	}

	/**
	 * 1首先设计一个JFrame,上面有一个按钮，文字是 "打开一个模态窗口"。 点击该按钮后，随即打开一个模态窗口。
	 * 在这个模态窗口中有一个按钮，文本是 "锁定大小", 点击后，这个模态窗口的大小就被锁定住，不能改变。 再次点击，就回复能够改变大小
	 */
	public static void resizable() {
		// 主窗体
		JFrame f = new JFrame("jisuanqi");
		f.setSize(800, 600);
		f.setLocation(200, 200);
		// f.setLayout(null);

		JDialog d = new JDialog(f);
		// 设置为模态
		d.setModal(true);

		d.setTitle("模态的对话框");
		d.setSize(400, 300);
		d.setLocation(250, 250);
		d.setLayout(null);
		JButton b1 = new JButton("锁定大小");
		b1.setBounds(50, 50, 240, 30);
		b1.addActionListener(new ActionListener() {
			// 锁定模态框大小
			public void actionPerformed(ActionEvent e) {
				d.setResizable(!d.isResizable());
			}
		});
		d.add(b1);

		// 按钮
		JButton b = new JButton("打开一个模态对话框");
		b.setBounds(50, 50, 280, 30);

		b.addActionListener(new ActionListener() {
			// 当按钮被点击时，就会触发 ActionEvent事件
			// actionPerformed 方法就会被执行
			public void actionPerformed(ActionEvent e) {
				d.setVisible(true);
			}
		});
		f.add(b);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public static void modalJDialog() {
		JFrame f = new JFrame("外部窗体");
		f.setSize(800, 600);
		f.setLocation(100, 100);

		// 根据外部窗体实例化JDialog
		JDialog d = new JDialog(f);
		// 设置为模态
		d.setModal(true);

		d.setTitle("模态的对话框");
		d.setSize(400, 300);
		d.setLocation(200, 200);
		d.setLayout(null);
		JButton b = new JButton("一键秒对方基地挂");
		b.setBounds(50, 50, 280, 30);
		d.add(b);

		f.setVisible(true);
		d.setVisible(true);
	}

	public static void jFrame() {
		// 主窗体
		JFrame f = new JFrame("LoL");
		// 主窗体设置大小
		f.setSize(400, 300);
		// 主窗体设置位置
		f.setLocation(200, 200);
		// 主窗体中的组件设置为绝对定位
		f.setLayout(null);

		// 按钮组件
		JButton b = new JButton("一键秒对方基地挂");
		// 同时设置组件的大小和位置
		b.setBounds(50, 50, 280, 30);
		// 把按钮加入到主窗体中
		f.add(b);

		// 关闭窗体的时候，退出程序
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 让窗体变得可见
		f.setVisible(true);
	}

	public static void jDialog() {
		// 普通的窗体，带最大和最小化按钮，而对话框却不带
		JDialog d = new JDialog();
		d.setTitle("LOL");
		d.setSize(400, 300);
		d.setLocation(200, 200);
		d.setLayout(null);
		JButton b = new JButton("一键秒对方基地挂");
		b.setBounds(50, 50, 280, 30);

		d.add(b);

		d.setVisible(true);
	}
}