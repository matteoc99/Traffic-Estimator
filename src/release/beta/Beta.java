package release.beta;

import logic.city.City;
import main.Main;
import parsing.OsmToJsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Matteo Cosi
 * @since 05.03.2018
 */
public class Beta extends JFrame {

    Container c;

    // TODO: 05.03.2018 All the files should be replaced with directories that exist on every pc! not vehicles/driving. etc

    public Beta() {
        setTitle("Traffic-Estimator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.width / 4, dimension.height / 2);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);



        JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/rom.PNG"))));
        background.setLocation(0, 0);
        background.setSize(getSize());

        JLabel title = new JLabel("Traffic Estimator");

        JButton startFromJson = new JButton("Create city from JSON/OSM");
        JButton getOsmFile = new JButton("Get an OSM file");

        Insets i = getInsets();

        title.setBounds(16 - i.left, 16, getWidth() - 32 - i.left + i.right, 80);

        startFromJson.setBounds(16 - i.left, (getHeight() / 4) + 16 - i.top, getWidth() - 32 - i.left + i.right, 80);
        getOsmFile.setBounds(16 - i.left, (getHeight() / 4) * 2 + 16 - i.top, getWidth() - 32 - i.left + i.right, 80);

        startFromJson.setBackground(new Color(55, 55, 55));
        getOsmFile.setBackground(new Color(55, 55, 55));

        Font buttonFont = new Font("Arial", Font.BOLD, 25);
        Font titleFont = new Font("Arial", Font.BOLD, 40);

        startFromJson.setFont(buttonFont);
        getOsmFile.setFont(buttonFont);
        title.setFont(titleFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        startFromJson.setOpaque(false);
        getOsmFile.setOpaque(false);
        startFromJson.setContentAreaFilled(false);
        getOsmFile.setContentAreaFilled(false);
        startFromJson.setBorderPainted(false);
        getOsmFile.setBorderPainted(false);

        title.setForeground(Color.WHITE);
        startFromJson.setForeground(Color.WHITE);
        getOsmFile.setForeground(Color.WHITE);


        c.add(background, 0);
        c.add(startFromJson, 0);
        c.add(getOsmFile, 0);
        c.add(title, 0);
        repaint();

        startFromJson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser=new JFileChooser(System.getProperty("user.dir")+"\\src\\parsing\\res");
                chooser.showSaveDialog(null);
                String path=chooser.getSelectedFile().getAbsolutePath().substring(0,chooser.getSelectedFile().getAbsolutePath().lastIndexOf('\\'));
                //getFileName
                String name = chooser.getSelectedFile().getName().substring(0,chooser.getSelectedFile().getName().indexOf('.'));

                String ending= chooser.getSelectedFile().getName().substring(chooser.getSelectedFile().getName().indexOf('.'));

                switch (ending) {
                    case ".json": {
                        dispose();
                        Main main = new Main(City.createCityFromJson(new File(path + "/" + name + ending)));
                        break;
                    }
                    case ".osm": {
                        dispose();
                        OsmToJsonParser.parse(path + "/" + name + ending, path + "/" + name + ".json");
                        Main main = new Main(City.createCityFromJson(new File(path + "/" + name + ".json")));
                        break;
                    }
                    default:
                        new JOptionPane("The selected file is not an OSM or JSON file").createDialog("Warning").setVisible(true);
                        break;
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                startFromJson.setOpaque(true);
                startFromJson.setForeground(new Color(0, 255, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startFromJson.setOpaque(false);
                startFromJson.setForeground(Color.white);
            }
        });


        getOsmFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new JOptionPane("STEP 1: go to the open street map website\n" +
                        "STEP 2: click export and select the desired square to export\n" +
                        "STEP 3: come back to this application and click: "+startFromJson.getText()).createDialog("Instructions").setVisible(true);
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.openstreetmap.org"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getOsmFile.setOpaque(true);
                getOsmFile.setForeground(new Color(0, 255, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                getOsmFile.setOpaque(false);
                getOsmFile.setForeground(Color.white);

            }
        });

    }

    public static void main(String[] args) {
        new Beta();
    }
}