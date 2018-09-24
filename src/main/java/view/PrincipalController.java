package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.Cilindro;
import model.FiguraGeo3d;
import model.Material;
import model.Paralelepipedo;
import model.Cone;
import model.Esfera;

public class PrincipalController implements Initializable {

    private List<Material> lstMaterial = new ArrayList<>();
    private List<FiguraGeo3d> lstObj = new ArrayList<FiguraGeo3d>();
    private Material M;
    private final NumberFormat nf
            = NumberFormat.getInstance(Locale.getDefault());
    @FXML
    private ComboBox cmbMateriais;
    @FXML
    private Label lblSegundo;
    @FXML
    private VBox vboxSegundo;
    @FXML
    private VBox vboxTerceiro;
    @FXML
    private RadioButton rdBtnParalelepipedo;
    @FXML
    private RadioButton rdBtnCilindro;
    @FXML
    private RadioButton rdBtnCone;
    @FXML
    private TextField txtFldPrimeiro;
    @FXML
    private TextField txtFldSegundo;
    @FXML
    private TextField txtFldTerceiro;
    @FXML
    private ToggleGroup figura;
    @FXML
    private Label lblJanela;
    @FXML
    private Button btnAdicionarClick;
    @FXML
    private VBox vboxMostraDados;
    @FXML
    private Label lblObjetoPesado;
    @FXML
    private Label lblPesoPesado;
    @FXML
    private Label lblPesoTotal;
    @FXML
    private Label lblVolumeTotal;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblMaterialPesado;
    @FXML
    private CheckBox chckbxFragil;
    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    @FXML
    private void limpaTela() {
        txtFldPrimeiro.clear();
        txtFldSegundo.clear();
        txtFldTerceiro.clear();
        cmbMateriais.getSelectionModel().clearSelection();
        chckbxFragil.selectedProperty().set(false);

    }

    @FXML
    public void MostraDados() {
        lblPesoTotal.setText(String.format("%.2f", getPesoTotal()));
        lblVolumeTotal.setText(String.format("%.2f", getVolumeTotal()));
        lblTotal.setText(String.valueOf(getTotal()));
        getObjetoPesado();
    }

    public double getPesoTotal() {
        double peso = 0;
        for (FiguraGeo3d f : lstObj) {
            peso = peso + f.getPeso();
        }
        return peso;
    }

    public double getVolumeTotal() {
        double volume = 0;
        double aux = 0;
        for (FiguraGeo3d f : lstObj) {
            if (f instanceof Paralelepipedo) {
                volume = volume + (((Paralelepipedo) f).getVolume());
            } else if (f instanceof Cilindro) {
                volume = volume + (((Cilindro) f).getVolume());
            } else if (f instanceof Cone) {
                volume = volume + (((Cone) f).getVolume());
            } else if (f instanceof Esfera) {
                volume = volume + (((Esfera) f).getVolume());
            }
        }
        return volume;
    }

    @FXML
    public void getObjetoPesado() {
        double peso = 0;
        FiguraGeo3d figAux = null;
        Material matAux = null;
        for (FiguraGeo3d f : lstObj) {
            if (f.getPeso() > peso) {
                peso = f.getPeso();
                figAux = f;
                matAux = f.getMaterial();
            }
        }
        if (figAux != null && matAux != null) {
            lblObjetoPesado.setText(figAux.toString());
            lblPesoPesado.setText(String.format("%.2f", peso));
            lblMaterialPesado.setText(matAux.toString());
        }
    }

    public int getTotal() {
        return lstObj.size();
    }

    @FXML
    private void btnAdicionarClick(Event event) {
        M = (Material) cmbMateriais.getSelectionModel().getSelectedItem();
        double altura;
        try {
            altura = nf.parse(txtFldPrimeiro.getText()).doubleValue();

            Material m = (Material) cmbMateriais.getSelectionModel().getSelectedItem();
            if (rdBtnParalelepipedo.isSelected()) {
                double largura = nf.parse(txtFldSegundo.getText()).doubleValue();
                double profundidade = nf.parse(txtFldTerceiro.getText()).doubleValue();
                Paralelepipedo p = new Paralelepipedo(altura, largura, profundidade, m,chckbxFragil.isSelected());
                lstObj.add(p);
                System.out.println(String.format("Objeto com base circular: %b",p.baseCircular()));
            } else if (rdBtnCilindro.isSelected()) {
                double diametro = nf.parse(txtFldSegundo.getText()).doubleValue();
                Cilindro ci = new Cilindro(altura, diametro, m,chckbxFragil.isSelected());
                lstObj.add(ci);
                System.out.println(String.format("Objeto com base circular: %b",ci.baseCircular()));
            } else if (rdBtnCone.isSelected()) {
                double diametro = nf.parse(txtFldSegundo.getText()).doubleValue();
                Cone co = new Cone(altura, diametro, m,chckbxFragil.isSelected());
                lstObj.add(co);
                System.out.println(String.format("Objeto com base circular: %b",co.baseCircular()));
            } else {
                double diametro = nf.parse(txtFldSegundo.getText()).doubleValue();
                Esfera e = new Esfera(altura, diametro, m,chckbxFragil.isSelected());
                lstObj.add(e);
                System.out.println(String.format("Objeto com base circular: %b",e.baseCircular()));
            }
        } catch (ParseException ex) {
            System.out.println("Erro no metodo btnAdicionarClick!");
        }
        vboxMostraDados.setVisible(true);
        MostraDados();
        limpaTela();
        
    }

    @FXML
    private void rdBtnParalelepipedoSelect() {
        vboxSegundo.setVisible(true);
        vboxTerceiro.setVisible(true);
        lblSegundo.setText("Largura");
        btnAdicionarClick.disableProperty().bind(txtFldPrimeiro.textProperty().isEmpty()
                .or(txtFldSegundo.textProperty().isEmpty())
                .or(txtFldTerceiro.textProperty().isEmpty())
                .or(cmbMateriais.getSelectionModel().selectedItemProperty().isNull()));
    }

    @FXML
    private void rdBtnCilindroSelect() {
        lblSegundo.setText("Diametro");
        vboxSegundo.setVisible(true);
        vboxTerceiro.setVisible(false);
        btnAdicionarClick.disableProperty().bind(txtFldPrimeiro.textProperty().isEmpty()
                .or(txtFldSegundo.textProperty().isEmpty())
                .or(cmbMateriais.getSelectionModel().selectedItemProperty().isNull()));
    }

    @FXML
    private void rdBtnEsferaSelect() {
        lblSegundo.setText("Diametro");
        vboxSegundo.setVisible(true);
        vboxTerceiro.setVisible(false);
        btnAdicionarClick.disableProperty().bind(txtFldPrimeiro.textProperty().isEmpty()
                .or(txtFldSegundo.textProperty().isEmpty())
                .or(cmbMateriais.getSelectionModel().selectedItemProperty().isNull()));
    }

    @FXML
    private void rdBtnConeSelect() {
        lblSegundo.setText("Diametro");
        vboxTerceiro.setVisible(false);
        btnAdicionarClick.disableProperty().bind(txtFldPrimeiro.textProperty().isEmpty()
                .or(txtFldSegundo.textProperty().isEmpty())
                .or(cmbMateriais.getSelectionModel().selectedItemProperty().isNull()));

    }

    private final ChangeListener<? super String> listenerPrimeiro
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                && !newValue.isEmpty()) {
                    txtFldPrimeiro.setText(oldValue);
                } else {
                    txtFldPrimeiro.setText(newValue);
                }
            };

    private final ChangeListener<? super String> listenerSegundo
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                && !newValue.isEmpty()) {
                    txtFldSegundo.setText(oldValue);
                } else {
                    txtFldSegundo.setText(newValue);
                }
            };

    private final ChangeListener<? super String> listenerTerceiro
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\" + separadorDecimal + "\\d*)?")
                && !newValue.isEmpty()) {
                    txtFldTerceiro.setText(oldValue);
                } else {
                    txtFldTerceiro.setText(newValue);
                }
            };

    public FiguraGeo3d getConeMaisLeve() {
        Cone aux = null;
        for (FiguraGeo3d f : lstObj) {
            if (f instanceof Cone) {
                if (aux == null) {
                    aux = (Cone) f;
                } else {
                    if (f.getPeso() < aux.getPeso()) {
                        aux = (Cone) f;
                    }
                }
            }
        }

        return aux;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lstMaterial.add(new Material("Aço", 1.5));
        lstMaterial.add(new Material("Chumbo", 5));
        lstMaterial.add(new Material("Cobre", -4));
        lstMaterial.add(new Material("Aluminio", 3));

        cmbMateriais.setItems(FXCollections.observableList(lstMaterial));

        txtFldPrimeiro.textProperty().addListener(listenerPrimeiro);
        txtFldSegundo.textProperty().addListener(listenerSegundo);
        txtFldTerceiro.textProperty().addListener(listenerTerceiro);

        btnAdicionarClick.disableProperty().bind(txtFldPrimeiro.textProperty().isEmpty()
                .or(txtFldSegundo.textProperty().isEmpty())
                .or(txtFldTerceiro.textProperty().isEmpty())
                .or(cmbMateriais.getSelectionModel().selectedItemProperty().isNull()));

        figura.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (rdBtnParalelepipedo.isSelected()) {
                    rdBtnParalelepipedoSelect();
                } else if (rdBtnCilindro.isSelected()) {
                    rdBtnCilindroSelect();
                } else if (rdBtnCone.isSelected()) {
                    rdBtnConeSelect();
                } else {
                    rdBtnEsferaSelect();
                }
            }
        });

    }
}
