package GUI;

import model.responseModel;
import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GUIForm {
    private JButton SUBMITButton;
    private JTextField Msg;
    private JTextField Status;
    private JTextField Comment;
    private JButton CLOSE;
    private JPanel panel1;
    private JButton minimize;


    public GUIForm() {

        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConnectURI koneksiSaya = new ConnectURI();
                    URL myAdress = ConnectURI.buildURL
                            ("https://harber.mimoapps.xyz/api/getaccess.php");
                    String response = ConnectURI.getResponseFromHttpUrl(myAdress);
                    System.out.println(response);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<responseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        responseModel resModel = new responseModel();
                        JSONObject myJSONobject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONobject.getString("message"));
                        resModel.setStatus(myJSONobject.getString("status"));
                        resModel.setComment(myJSONobject.getString("comment"));
                        responseModel.add(resModel);

                        System.out.println("response are : ");
                        for (int index = 0; index < responseModel.size(); index++) {
                            Msg.setText(responseModel.get(index).getMsg());
                            Status.setText(responseModel.get(index).getStatus());
                            Comment.setText(responseModel.get(index).getComment());
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        minimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Msg.setText("");
                Status.setText("");
                Comment.setText("");


            }
        });

        CLOSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Msg.setText("");
                Status.setText("");
                Comment.setText("");
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI FORM");
        frame.setContentPane(new GUIForm().panel1);
        frame.setUndecorated(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
