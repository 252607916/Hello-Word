// ReciteStat.java
package whypro.memorize;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class ReciteStat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7247900451593318205L;
	
	final JTable table;
	Vector<ReciteRecord> recordsVector;
	
	public ReciteStat() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		recordsVector = RRecordGetter.loadRRecords("./record/recite.rec", 100);
		Object[][] o = new Object[recordsVector.size()][5];
		int j = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String str = null;
		
		for (ReciteRecord i : recordsVector) {
			o [j][0] = i.word;
			str = format.format(new Date(i.startDate));
			o [j][1] = str;
			str = format.format(new Date(i.lastDate));
			o [j][2] = str;
			o [j][3] = i.stage;
			o [j][4] = i.strange;
			++j;
		}
		
		String[] names = {"����", "�״μ���ʱ��", "�ϴμ���ʱ��", "����׶�", "İ����"}; 
		table = new JTable(o, names) {
			// ���ʻ�ͳ����Ϊֻ��
			public boolean isCellEditable(int row, int column) {
				return false;  
			}
		};

		mainPanel.add(new JScrollPane(table));
		
		this.add(mainPanel);
		// 
		this.setTitle("�������ͳ��");
		this.setSize(600, 400);
		this.setResizable(false);

		// ʹ���ھ���
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width / 2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height / 2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth - width / 2, screenHeight - height / 2);

		this.setVisible(true);
	}

}