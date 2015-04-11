package mx.cinvestav.gdl.iot.webpage.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

public class EpWPDatas extends IoTEntryPoint {
	private DialogBox dbWait = new DialogBox();

	private VerticalPanel formPanel = new VerticalPanel();

	private VerticalPanel formChart = new VerticalPanel();

	private FlexTable tableData = new FlexTable();
	private ListBox lbController = new ListBox();

	private ListBox lbSmartThing = new ListBox();

	private ListBox lbSensor = new ListBox();
	private ListBox lbTypeSensor = new ListBox();

	private DateBox dbFrom = new DateBox();
	private DateBox dbTo = new DateBox();

	private Button btGenerate = new Button("Generate");
	private List<ControllerDTO> CONTROLLERS;
	private List<SmartThingDTO> SMARTTHINGS;
	private List<SensorDTO> SENSORS;

	private Map<String, List<MeasureDTO>> group;
	private String measure_unit = "";

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);

	@Override
	public void continueModuleLoad() {

		showDialogWait();
		entityService.getEntity(new ControllerDTO(), null, new AsyncCallback<List<ControllerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<ControllerDTO> result) {
				dbWait.hide();
				CONTROLLERS = result;
				lbController.addItem("Select...");

				for (ControllerDTO c : CONTROLLERS) {
					lbController.addItem(c.getName(), c.getId() + "");
				}
			}
		});

		tableData.setText(0, 0, "Controller: ");
		tableData.setWidget(0, 1, lbController);

		tableData.setText(1, 0, "SmartThing: ");
		tableData.setWidget(1, 1, lbSmartThing);

		tableData.setText(2, 0, "Type sensor: ");
		tableData.setWidget(2, 1, lbTypeSensor);

		tableData.setText(3, 0, "From date: ");
		tableData.setWidget(3, 1, dbFrom);

		tableData.setText(4, 0, "To date: ");
		tableData.setWidget(4, 1, dbTo);

		lbSmartThing.setEnabled(false);
		lbSensor.setEnabled(false);

		formPanel.add(tableData);
		formPanel.add(btGenerate);
		formPanel.setCellHorizontalAlignment(btGenerate, HasHorizontalAlignment.ALIGN_LEFT);
		RootPanel.get("formContainer").add(formPanel);

		DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat("MMMM dd yyyy"));
		dbFrom.setFormat(format);
		dbTo.setFormat(format);
		dbFrom.getDatePicker().setYearArrowsVisible(true);
		dbTo.getDatePicker().setYearArrowsVisible(true);

		lbController.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				final int idController = Integer.parseInt(lbController.getValue(lbController.getSelectedIndex()));

				entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SmartThingDTO> result) {
						SMARTTHINGS = result;
						lbSmartThing.clear();

						lbSmartThing.addItem("Select...");

						lbSmartThing.setEnabled(true);

						for (SmartThingDTO c : SMARTTHINGS) {
							if (c.getIdcontroller() == idController) {
								lbSmartThing.addItem(c.getName(), c.getId() + "");
							}
						}

					}
				});

			}
		});

		lbSmartThing.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				final int idSmartThing = Integer.parseInt(lbSmartThing.getValue(lbSmartThing.getSelectedIndex()));
				entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SensorDTO> result) {
						SENSORS = result;
						lbSensor.clear();

						lbTypeSensor.clear();
						lbTypeSensor.addItem("Select...");
						lbSensor.setEnabled(true);

						for (SensorDTO c : SENSORS) {
							measure_unit = c.getUnit();
							if (c.getIdthing() == idSmartThing) {
								lbSensor.addItem(c.getSensor_type(), c.getId() + "");
							}
						}

						ArrayList<String> typeSensor = new ArrayList<String>();

						for (int i = 0; i < lbSensor.getItemCount(); i++) {
							if (!typeSensor.contains(lbSensor.getItemText(i))) {
								typeSensor.add(lbSensor.getItemText(i));
								lbTypeSensor.addItem(lbSensor.getItemText(i));
							}
						}

					}
				});

			}
		});

		btGenerate.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				group = new HashMap<String, List<MeasureDTO>>();
				formChart.clear();

				final String type = lbTypeSensor.getItemText(lbTypeSensor.getSelectedIndex());
				int s = 0;

				for (int i = 0; i < SENSORS.size(); i++) {
					if (SENSORS.get(i).getSensor_type().equals(type)) {
						s++;
					}
				}

				final int sf = s;

				for (int i = 0; i < SENSORS.size(); i++) {
					if (SENSORS.get(i).getSensor_type().equals(type)) {

						final String name = SENSORS.get(i).getName();
						Map<String, Boolean> filter = new HashMap<String, Boolean>();
						filter.put("ventilador", false);
						
						entityService.getSensorData(SENSORS.get(i).getId(), dbFrom.getValue(), dbTo.getValue(), filter,
								new AsyncCallback<List<MeasureDTO>>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onSuccess(final List<MeasureDTO> result) 
									{
										if (result.size() != 0) 
										{
											group.put(name, result);
											if (type.toUpperCase().equals("PHOTO")) 
											{
												int numRows = (int) result.size() / 5;
												int numColumns = 5;
												Grid grid = new Grid(numRows+1, numColumns);
												grid.setCellPadding(3);
												int i = 0;

												for (int row = 0; row <= numRows; row++) {
													for (int col = 0; col < numColumns; col++) {

														if (i < result.size()) {
															byte[] image2 = result.get(i).getImage();
															
															char[] encode = Base64Coder.encode(image2);
															String x = new String(encode);

															Image image = new Image();
															
															// String b =
															// "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";

															image.setUrl("data:image/png;base64," + x);
															image.setSize("25%", "25%");

															Image imageFull = new Image();
															imageFull.setUrl("data:image/png;base64," + x);

															final DialogBox imagePopup2 = new DialogBox();
															imagePopup2.setAnimationEnabled(true);
															imagePopup2.setGlassEnabled(true);
															imagePopup2.setText(result.get(i).getMeasure_date() + "");
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
														i++;
													}
												}

												DecoratorPanel decPanel = new DecoratorPanel();
												decPanel.add(grid);
												
												formPanel.add(decPanel);
												formPanel.setCellHorizontalAlignment(decPanel,
														HasHorizontalAlignment.ALIGN_CENTER);

											}
										}
										else 
										{
											group.put(name, null);
										}
										
										// last result has arrived, process data
										if (group.size() == sf) 
										{
											String data = GraphUtils.generateStringData(group);
											if(data == null)
											{
												GraphUtils.hideNVD3();
												
												final DialogBox noDatas = new DialogBox();
												noDatas.setAnimationEnabled(true);
												noDatas.setGlassEnabled(true);
												noDatas.setText("There is no data for display.");
												Button close=new Button("Close");
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
												GraphUtils.generateNVD3(measure_unit, "", data);
											}
										}
									}
								});
					}
				}

			}
		});
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
