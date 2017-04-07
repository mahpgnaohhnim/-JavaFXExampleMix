package re.javafx.example.starter.azubi;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import re.javafx.example.CopyWindow.CopyWindow;
import re.javafx.example.CopyWindow.CopyWindowEx;
import re.javafx.example.EasyWindow.EasyWindow;
import re.javafx.example.EasyWindow.EasyWindowEx;
import re.javafx.example.ToDoList.ToDoList;
import re.javafx.example.ToDoList.ToDoListDX;
import re.javafx.example.ToDoList.ToDoListEx;

import java.util.Optional;

/*
 * author: Re
 * content: Leerer Workspace für JavaFX Teachware / Teil 1
 */

// TODO: Import Übungs-Package T1.1 bis T1.4
// z.B. import re.javafx.example.EasyWindow.*;

public class FxExampleStarter extends Application {
	// TODO, T0.3: Deklaration aller UI-Controls wie Hyperlink-Array
	Hyperlink arrHLExamples[] = new Hyperlink[]{
			new Hyperlink("Home"),
			new Hyperlink("Chess"),
			new Hyperlink("CopyWindow"),
			new Hyperlink("CopyWindowEx"),
			new Hyperlink("EasyWindow"),
			new Hyperlink("EasyWindowEx"),
			new Hyperlink("ToDoList"),
			new Hyperlink("ToDoListEx"),
			new Hyperlink("ToDoListDX"),
			new Hyperlink("Beenden")
	};

	// Deklaration der Main-Scene
	Scene scMain;

	// TODO, T0.3: Mitte der Applikation besteht aus Menü (links) und Beispielprogramm (rechts)
	//       (d.h. hier brauchen wir ein SplitPane)
	SplitPane sp;

	
	// TODO, T0.3: Wir erzeugen einen Rahmen auf der rechten Seite, der Init-Screen oder später unsere 
	// Übungen platziert werden...
	StackPane spRightSide;

	@Override
	public void init() throws Exception {
		/*
		 * Standardmäßig wird diese Methode nicht überschrieben und ist leer.
		 * Sie kann u.a. genutzt werden um ... - Aufrufparameter auszuwerten 
		 * 
		 * Parameters p; 
		 * p = getParameters(); 
		 * List<String> 
		 * p.getRaw();
		 *  
		 * // Jeder Listeneintrag entspricht einem Argument! - ggf. Konfigurationsdateien
		 * auslesen und Attribute füllen, etc. !! WARNUNG: UI - Elemente ERST IN
		 * DER start(...) - Methode erzeugen !!
		 */
	};

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Wir verwenden eine BorderPane für unsere "root-" Node
		BorderPane bpRoot = new BorderPane();

		// TODO, T0.1: Befülle BorderPane mit dem Nordteil
		bpRoot.setTop(addTopHeader());

		// TODO, T0.2: Befülle BorderPane mit dem Südteil
		bpRoot.setBottom(addBottom());
		
		// TODO: T0.3: Erzeuge rechten Teil (leerer Dummy) der SplitPane / T0.5: alternative Init-Screens
		spRightSide = new StackPane();
		spRightSide = addAnimatedInitScreen(addInitScreen());

		
		// TODO, T0.3: Baue das SplitPane im CENTER ein und erzeuge SplitPane mit Menüstruktur
		bpRoot.setCenter(addSplitPane(addMenueVBox(arrHLExamples), spRightSide));

		// TODO, T0.4: Funktionalität für 'alle Hyperlinks' (auslagern)...
		for(Hyperlink val: arrHLExamples){
			val.setOnAction(e ->{
				handleHyperlinkAction(e);
			});
		}
		
		// TODO, T0.5: SplitPane Schönheitskorrektur: Maximiert oder nicht
		primaryStage.maxHeightProperty().addListener(e ->{
			if(((ReadOnlyBooleanProperty) e).getValue()){
				sp.setDividerPosition(0, 0.11);
			}else{
				sp.setDividerPosition(0,0.24);
			}
		});
		
		// Die Szene, die "gespielt" werden soll aufbauen / vorbereiten...
		scMain = new Scene(bpRoot, 700, 550);

		// TODO, T1.1: CSS-Sheet hinzufügen für Aufgabe 1-1: EasyWindowEx
		scMain.getStylesheets().add("EasyWindowEx-Styles.css");

		// Die Bühne (das Hauptfenster) benennen und die Szene zeigen...
		primaryStage.setTitle("BS-Info Henry FX-Starter");
		// TODO, T0.5: System-Icon -im Hauptfenster ganz links- adden
		primaryStage.getIcons().add(new Image("logo_icon_plain.png"));
		
		primaryStage.setScene(scMain);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		// Do some other stuff on EXITING ??? SAVING ???
		System.out.println("Exiting...");
	}

	/*
	 * TODO, T0.1: Methode, generiert den oberen Teil der Starter-App -> Enthält das
	 * RBS Logo rechts oben sowie einen großen Titel als Text-Element
	 */
	private AnchorPane addTopHeader(){
		AnchorPane ap = new AnchorPane();
		Text txtTitle = new Text("Henryteach JavaFX GUI");
		Text txtSubtitle = new Text("Pham Minh Hoang, 2017");
		txtSubtitle.setFill(Color.WHITE);
		txtTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 22));
		txtTitle.setFill(Color.WHITE);

		ImageView imgRbsLogo = new ImageView(new Image("lhm.jpg",227,123, true, true));

		ap.getChildren().addAll(txtTitle,txtSubtitle, imgRbsLogo);

		AnchorPane.setRightAnchor(imgRbsLogo, 0.0);
		AnchorPane.setTopAnchor(imgRbsLogo, 0.0);
		AnchorPane.setTopAnchor(txtTitle, 30.0);
		AnchorPane.setLeftAnchor(txtTitle, 20.0);
		AnchorPane.setLeftAnchor(txtSubtitle, 20.0);
		AnchorPane.setBottomAnchor(txtSubtitle, 20.0);

		ap.setStyle("-fx-background-color: #336699");
		return ap;
	}

	/*
	 * TODO, T0.2: Methode, generiert den unteren Teil der Starter-App -> Enthält das
	 * Logo der Schule sowie eine URL zur Schulhomepage
	 */
	private StackPane addBottom(){
		ImageView imgSchoolLogo = new ImageView(new Image("logo_wide.png", 200,100, true, true));
		Hyperlink hlinkHomepage = new Hyperlink("https://www.bsinfo.eu");
		hlinkHomepage.setTextFill(new Color(0,0,0,1));
		hlinkHomepage.setOnAction(e ->{
			getHostServices().showDocument(hlinkHomepage.getText());
		});

		StackPane sp = new StackPane();
		sp.setStyle("-fx-background-color: linear-gradient(to right, #FFFFFF, #6DABDD)");
		sp.getChildren().addAll(imgSchoolLogo, hlinkHomepage);
		StackPane.setAlignment(imgSchoolLogo, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(hlinkHomepage, Pos.BOTTOM_RIGHT);

		return sp;
	}

	/*
	 * TODO, T0.3: Methode, erzeugt einen horizontalen Splitscreen (links,rechts) mit den übergebenen Nodes 
	 */
	private SplitPane addSplitPane(Node nodeleft, Node nodeRight){
		sp = new SplitPane();
		sp.getItems().addAll(nodeleft, nodeRight);
		sp.setDividerPosition(0, 0.24);
		return sp;
	}

	
	/*
	 * TODO, T0.3: Methode, erzeugt VBox, die das übergebene Array von Hyperlinks (Init,...,Beenden)
	 * untereinander anordnet 
	 */
	private VBox addMenueVBox(Hyperlink[] arrHyperlinks){
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setSpacing(10);

		Text title = new Text("Auswahl");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		vBox.getChildren().add(title);

		for(int i = 0; i<arrHyperlinks.length; i++){
			if(i<arrHyperlinks.length-1){
				VBox.setMargin(arrHyperlinks[i], new Insets(0,0,0,8));
			}else{
				arrHyperlinks[i].setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
				arrHyperlinks[i].setTextFill(Color.RED);
			}
			vBox.getChildren().add(arrHyperlinks[i]);
		}
		return vBox;
	}
	

	/*
	 * TODO. T0.4: Methode erzeugt eine Messagebox mit übergebenem Text/Titel und 1 oder 2 Optionen (OK/Cancel) ...
	 */

	private int showMsgBox(String strMessage, String strTitle, int iOptions){
		Alert alert = null;

		if(iOptions == 2){
			alert = new Alert(Alert.AlertType.INFORMATION, strMessage, ButtonType.OK, ButtonType.CANCEL);
		}else{
			alert = new Alert(Alert.AlertType.INFORMATION, strMessage);
		}

		if(strTitle.equals("")){
			strTitle = "Hinweis";
		}

		alert.setHeaderText("");
		alert.setTitle(strTitle);

		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() == ButtonType.OK){
			return 1;
		}else {
			return 0;
		}
	}

	
	/*
	 * TODO, T0.4: Methode, die die Funktionalität beim Drücken auf die Hyperlinks realisiert!
	 */
	public void handleHyperlinkAction(ActionEvent event){
		spRightSide.getChildren().clear();

		Hyperlink hlink = (Hyperlink) event.getSource();
		switch(hlink.getText()){
			case "Home":
				spRightSide.getChildren().addAll(addAnimatedInitScreen(addInitScreen()));
				break;
			case "Chess":
				Chess chess = new Chess();
				spRightSide.getChildren().add(chess);
				break;
			case "CopyWindow":
				CopyWindow copyWindow = new CopyWindow();
				spRightSide.getChildren().addAll(copyWindow);
				break;
			case "CopyWindowEx":
				CopyWindowEx copyWindowEx = new CopyWindowEx();
				spRightSide.getChildren().addAll(copyWindowEx);
				break;
			case "EasyWindow":
				EasyWindow easyPeasyWin = new EasyWindow();
				spRightSide.getChildren().addAll(easyPeasyWin);
				break;
			case "EasyWindowEx":
				EasyWindowEx easyPeasyWinEx = new EasyWindowEx();
				spRightSide.getChildren().add(easyPeasyWinEx);
				break;
			case "ToDoList":
				ToDoList toDoList = new ToDoList();
				spRightSide.getChildren().add(toDoList);
				break;
			case "ToDoListEx":
				ToDoListEx toDoListEx = new ToDoListEx();
				spRightSide.getChildren().add(toDoListEx);
				break;
			case "ToDoListDX":
				ToDoListDX toDoListDX = new ToDoListDX();
				spRightSide.getChildren().add(toDoListDX);
				break;
			case "Beenden":
				if(showMsgBox("Wirklich Beenden?", "Hinweis", 2) == 1){
					Platform.exit();
				}
				break;
			default:
				showMsgBox("Verzeihung, " + hlink.getText() + " gibt´s nicht!", "Hinweis", 1);
				break;
		}
	}

	
	/*
	 * TODO, T0.5: Methode erzeugt ein Standard StackPane Layout mit radial-gradient
	 * Background (wird genutzt als Start/Init Hintergrund)
	 */
	private StackPane addInitScreen(){

		StackPane spInit = new StackPane();
		spInit.setStyle("-fx-background-color: radial-gradient(radius 100%, #DAE6F3, darkgray, white);");

		return spInit;
	}

	
	/*
	 * TODO, T0.5: Methode zum erweitern des Init-Screens um ein aninmiertes BS-Info Logo
	 */
	private StackPane addAnimatedInitScreen(StackPane spInit){

		Pane hb = new Pane();
		ImageView imgView = new ImageView(new Image("bsinfo_building_hp.jpg",
				spInit.getWidth(), spInit.getHeight(), true, true));
		imgView.setOpacity(0.75);
		hb.getChildren().addAll(imgView);
		imgView.fitHeightProperty().bind(hb.heightProperty());
		imgView.fitWidthProperty().bind(hb.widthProperty());

		Group root = new Group();

		ImageView imgLogo = new ImageView(new Image("logo_init.png",136,136,true, false));
		root.getChildren().add(imgLogo);

		Path path = new Path();
		path.getElements().addAll(new MoveTo(20,100), new CubicCurveTo(180, 60, 250, 340, 420, 240), new ClosePath());
		path.setStrokeWidth(0);

		root.getChildren().add(path);

		spInit.getChildren().addAll(hb, root);

		PathTransition pt = new PathTransition(Duration.millis(10000), path, imgLogo);
		pt.setCycleCount(Animation.INDEFINITE);
		pt.setAutoReverse(true);

		FadeTransition ft = new FadeTransition(Duration.millis(5000), imgLogo);
		ft.setFromValue(0.0);
		ft.setToValue(0.8);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.setAutoReverse(true);

		ParallelTransition ptr = new ParallelTransition();
		ptr.getChildren().addAll(pt,ft);
		ptr.play();

		return spInit;
	}
	

	
	// Java-Main Methode: startet lediglich die JavaFX- "Applikation"
	public static void main(String[] args) {
		// Methode der JavaFX-Application Class
		launch(args);
	}
}
