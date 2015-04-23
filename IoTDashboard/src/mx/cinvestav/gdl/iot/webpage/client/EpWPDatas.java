package mx.cinvestav.gdl.iot.webpage.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.ExperimentDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorTypeDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

public class EpWPDatas extends IoTEntryPoint {
	private DialogBox dbWait = new DialogBox();

	private VerticalPanel formPanel = new VerticalPanel();

	private VerticalPanel formChart = new VerticalPanel();
	private VerticalPanel formPictures = new VerticalPanel();

	private ListBox lbTypeSensorAll = new ListBox();
	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);

	private List<ControllerDTO> CONTROLLERS;
	private List<SmartThingDTO> SMARTTHINGS;
	private List<SensorDTO> SENSORS;
	private List<ExperimentDTO> EXPERIMENT;

	private Map<String, List<MeasureDTO>> group;
	//private String measure_unit = "";

	// Experiments
	private int e = 2;

	private FlexTable[] tableData = new FlexTable[e];
	private ListBox[] lbController = new ListBox[e];
	private ListBox[] lbSmartThing = new ListBox[e];
	private ListBox[] lbSensor = new ListBox[e];
	private ListBox[] lbTypeSensor = new ListBox[e];
	private ListBox[] lbExperiment = new ListBox[e];
	private TextArea[] taDescription = new TextArea[e];
	private TextArea[] taNotes = new TextArea[e];
	private DateBox[] dbFrom = new DateBox[e];
	private DateBox[] dbTo = new DateBox[e];
	private Button[] btGenerate = new Button[e];

	private VerticalPanel[] panelExperiment = new VerticalPanel[e];
	private DecoratorPanel[] decoratorPanel = new DecoratorPanel[e];

	private HorizontalPanel eAll = new HorizontalPanel();

	@Override
	public void continueModuleLoad() {

		showDialogWait();

		// Get all sensors's types
		entityService.getSensorType(new AsyncCallback<List<SensorTypeDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				dbWait.hide();
				// TODO:
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<SensorTypeDTO> result) {
				for (SensorTypeDTO c : result) {
					// Name ID
					lbTypeSensorAll.addItem(c.getName(), (c.getId() + ""));
				}

			}
		});

		eAll.setSpacing(10);

		// Experiments

		for (int i = 0; i < e; i++) {
			tableData[i] = new FlexTable();
			lbSmartThing[i] = new ListBox();
			lbSensor[i] = new ListBox();
			lbTypeSensor[i] = new ListBox();
			lbExperiment[i] = new ListBox();
			taDescription[i] = new TextArea();
			taNotes[i] = new TextArea();
			dbFrom[i] = new DateBox();
			dbTo[i] = new DateBox();
			btGenerate[i] = new Button("Generate");
			panelExperiment[i] = new VerticalPanel();
			decoratorPanel[i] = new DecoratorPanel();

			lbController[i] = new ListBox();
			lbController[i].addItem("Select...");

			final int a = i;
			// Get all controllers
			entityService.getEntity(new ControllerDTO(), null, new AsyncCallback<List<ControllerDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onSuccess(List<ControllerDTO> result) {
					dbWait.hide();
					CONTROLLERS = result;

					for (ControllerDTO c : CONTROLLERS) {
						lbController[a].addItem(c.getName(), c.getId() + "");
					}

				}
			});

			tableData[i].setText(0, 0, "Controller: ");
			tableData[i].setWidget(0, 1, lbController[i]);

			tableData[i].setText(1, 0, "SmartThing: ");
			tableData[i].setWidget(1, 1, lbSmartThing[i]);

			tableData[i].setText(2, 0, "Experiment: ");
			tableData[i].setWidget(2, 1, lbExperiment[i]);

			tableData[i].setText(3, 0, "Description: ");
			tableData[i].setWidget(3, 1, taDescription[i]);

			tableData[i].setText(4, 0, "Notes: ");
			tableData[i].setWidget(4, 1, taNotes[i]);

			tableData[i].setText(5, 0, "Type sensor: ");
			tableData[i].setWidget(5, 1, lbTypeSensor[i]);

			tableData[i].setText(6, 0, "From date: ");
			tableData[i].setWidget(6, 1, dbFrom[i]);

			tableData[i].setText(7, 0, "To date: ");
			tableData[i].setWidget(7, 1, dbTo[i]);

			DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat("MMMM dd yyyy"));
			dbFrom[i].setFormat(format);
			dbTo[i].setFormat(format);
			dbFrom[i].getDatePicker().setYearArrowsVisible(true);
			dbTo[i].getDatePicker().setYearArrowsVisible(true);

			dbFrom[i].setEnabled(false);
			dbTo[i].setEnabled(false);
			lbSmartThing[i].setEnabled(false);
			lbSensor[i].setEnabled(false);

			Label l1 = new Label("Statistics from ...");
			l1.setStyleName("lbProperty");
			panelExperiment[i].add(l1);
			panelExperiment[i].add(tableData[i]);
			panelExperiment[i].add(btGenerate[i]);
			panelExperiment[i].setCellHorizontalAlignment(btGenerate[i], HasHorizontalAlignment.ALIGN_RIGHT);

			decoratorPanel[i].add(panelExperiment[i]);
			eAll.add(decoratorPanel[i]);

		}

		formPanel.add(eAll);
		RootPanel.get("formContainer").add(formPanel);

		fillExperiments();
	}

	public void fillExperiments() {
		for (int i = 0; i < e; i++) {
			final int x = i;

			lbController[x].addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					final int idController = Integer.parseInt(lbController[x].getValue(lbController[x]
							.getSelectedIndex()));
					entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}

						@Override
						public void onSuccess(List<SmartThingDTO> result) {

							SMARTTHINGS = result;
							lbSmartThing[x].clear();
							lbSmartThing[x].addItem("Select...");
							lbSmartThing[x].setEnabled(true);
							for (SmartThingDTO c : SMARTTHINGS) {
								if (c.getIdcontroller() == idController) {
									lbSmartThing[x].addItem(c.getName(), c.getId() + "");
								}
							}

						}
					});

				}
			});

			lbSmartThing[x].addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {

					final int idSmartThing = Integer.parseInt(lbSmartThing[x].getValue(lbSmartThing[x]
							.getSelectedIndex()));

					entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}

						@Override
						public void onSuccess(List<SensorDTO> result) {
							SENSORS = result;
							lbSensor[x].clear();

							lbTypeSensor[x].clear();
							lbSensor[x].setEnabled(true);

							for (SensorDTO c : SENSORS) {
								if (c.getIdthing() == idSmartThing) {
									// Name Id
									lbSensor[x].addItem(getSensor_typeName(c.getSensor_type()), c.getSensor_type());
								}
							}

							ArrayList<String> typeSensor = new ArrayList<String>();

							for (int i = 0; i < lbSensor[x].getItemCount(); i++) {
								if (!typeSensor.contains(lbSensor[x].getValue(i))) {
									typeSensor.add(lbSensor[x].getValue(i));
									// Name Id
									lbTypeSensor[x].addItem(lbSensor[x].getItemText(i), lbSensor[x].getValue(i));
								}
							}
						}
					});

					entityService.getEntity(new ExperimentDTO(), null, new AsyncCallback<List<ExperimentDTO>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
						}

						@Override
						public void onSuccess(List<ExperimentDTO> result) {
							EXPERIMENT = result;
							lbExperiment[x].clear();

							lbExperiment[x].addItem("Select..", "-1");
							lbExperiment[x].addItem("None", "-1");

							for (ExperimentDTO c : EXPERIMENT) {
								if (c.getIdthing() == idSmartThing) {
									// Name Id
									String label = c.getName() + " : " + c.getDescription();
									lbExperiment[x].addItem(label, c.getId() + "");

								}
							}
						}
					});
				}
			});

			lbExperiment[x].addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					final int idExperiment = Integer.parseInt(lbExperiment[x].getValue(lbExperiment[x]
							.getSelectedIndex()));

					if (idExperiment == -1) {
						dbFrom[x].setEnabled(true);
						dbTo[x].setEnabled(true);

						taDescription[x].setText("");
						taNotes[x].setText("");
					} else {

						entityService.getEntity(new ExperimentDTO(), idExperiment,
								new AsyncCallback<List<ExperimentDTO>>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
									}

									@Override
									public void onSuccess(List<ExperimentDTO> result) {

										dbTo[x].setValue(result.get(0).getEnd_date());
										dbFrom[x].setValue(result.get(0).getStart_date());
										dbFrom[x].setEnabled(false);
										dbTo[x].setEnabled(false);

										taDescription[x].setText(result.get(0).getDescription());
										taNotes[x].setText(result.get(0).getNotes());
									}
								});
					}
				}
			});

			btGenerate[x].addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					dbWait.show();

					GraphUtils.hideNVD3(x);
					group = new HashMap<String, List<MeasureDTO>>();
					formChart.clear();
					formPictures.clear();
					final String type = lbTypeSensor[x].getValue(lbTypeSensor[x].getSelectedIndex());
					int s = 0;

					for (int i = 0; i < SENSORS.size(); i++) {
						if (SENSORS.get(i).getSensor_type().equals(type)) {
							s++;
						}
					}

					final int sf = s;

					for (int i = 0; i < SENSORS.size(); i++) {
						if (SENSORS.get(i).getSensor_type().equals(type)) {
							//final String name = SENSORS.get(i).getName();
							final String name = SENSORS.get(i).getDescription();
							entityService.getSensorData(SENSORS.get(i).getId(), Integer.parseInt(lbExperiment[x].getSelectedValue()), new AsyncCallback<List<MeasureDTO>>() {

										@Override
										public void onFailure(Throwable caught) {
											dbWait.hide();

										}

										@Override
										public void onSuccess(final List<MeasureDTO> result) {

											if (result.size() != 0) {
												group.put(name, result);
												if (type.toUpperCase().equals("PHOTO")) {
													int numRows = (int) result.size() / 5;
													int numColumns = 5;
													Grid grid = new Grid(numRows + 1, numColumns);
													grid.setCellPadding(3);
													int i = 0;

													for (int row = 0; row <= numRows; row++) {
														for (int col = 0; col < numColumns; col++, i++) {
															if (i < result.size()) {
																byte[] image2 = result.get(i).getImage();

																char[] encode = Base64Coder.encode(image2);
																String x = new String(encode);

																Image image = new Image();
																image.setUrl("data:image/png;base64," + x);
																image.setSize("25%", "25%");

																Image imageFull = new Image();
																imageFull.setUrl("data:image/png;base64," + x);

																final DialogBox imagePopup2 = new DialogBox();
																imagePopup2.setAnimationEnabled(true);
																imagePopup2.setGlassEnabled(true);
																imagePopup2.setText(result.get(i).getMeasure_date()
																		+ "");
																imagePopup2.setWidget(imageFull);

																imageFull.addClickHandler(new ClickHandler() {
																	public void onClick(ClickEvent event) {
																		imagePopup2.hide();
																	}
																});

																image.addClickHandler(new ClickHandler() {
																	public void onClick(ClickEvent event) {
																		imagePopup2.center();
																		imagePopup2.show();
																	}
																});
																grid.setWidget(row, col, image);
															} else {
																break;
															}
														}
													}

													DecoratorPanel decPanel = new DecoratorPanel();
													decPanel.add(grid);
													formPictures.add(decPanel);
													formPanel.add(formPictures);
													formPanel.setCellHorizontalAlignment(formPictures,
															HasHorizontalAlignment.ALIGN_CENTER);

												}
											} else {
												group.put(name, null);
											}

											// last result has arrived, process data
											if (group.size() == sf) {
												String data = GraphUtils.generateStringData(group);
												if (data == null) {

													final DialogBox noDatas = new DialogBox();
													noDatas.setAnimationEnabled(true);
													noDatas.setGlassEnabled(true);
													noDatas.setText("There is no data for display.");
													Button close = new Button("Close");
													close.addClickHandler(new ClickHandler() {
														public void onClick(ClickEvent event) {
															noDatas.hide();
														}
													});
													noDatas.setWidget(close);
													noDatas.show();
													noDatas.center();
												} 
												else 
												{
													GraphUtils.generateNVD3(getUnit(name), "Date", data, x, lbTypeSensor[x].getSelectedItemText());
												}
											}

											dbWait.hide();
										}
									});
						}
					}
				}
			});
		}

	}
	
	private String getUnit(String sensorName)
	{
		for(SensorDTO s : SENSORS)
		{
			if(s.getName().equals(sensorName)) return s.getUnit();
		}
		return "";
	}

	public String getSensor_typeName(String id) {
		String name = "";

		for (int i = 0; i < lbTypeSensorAll.getItemCount(); i++) {
			if (lbTypeSensorAll.getValue(i).equals(id)) {
				name = lbTypeSensorAll.getItemText(i);
			}
		}

		return name;
	}

	public void showDialogWait() {

		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

		VerticalPanel dialogContents = new VerticalPanel();

		dialogContents.setSpacing(4);

		Image image = new Image();

		image.setUrl(GWT.getHostPageBaseURL() + "images/loading2.gif");

		dialogContents.add(image);
		dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);

		dbWait.setWidget(dialogContents);
		dbWait.show();

	}
}
