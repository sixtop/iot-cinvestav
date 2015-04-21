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

public class EpWPDatas extends IoTEntryPoint
{
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
	private String measure_unit = "";

	// Experiment 1
	private FlexTable tableData = new FlexTable();
	private ListBox lbController = new ListBox();
	private ListBox lbSmartThing = new ListBox();
	private ListBox lbSensor = new ListBox();
	private ListBox lbTypeSensor = new ListBox();
	private ListBox lbExperiment = new ListBox();
	private TextArea taDescription = new TextArea();
	private TextArea taNotes = new TextArea();
	private DateBox dbFrom = new DateBox();
	private DateBox dbTo = new DateBox();
	private Button btGenerate = new Button("Generate");

	// Experiment 2
	private FlexTable tableData2 = new FlexTable();
	private ListBox lbController2 = new ListBox();
	private ListBox lbSmartThing2 = new ListBox();
	private ListBox lbSensor2 = new ListBox();
	private ListBox lbTypeSensor2 = new ListBox();
	private ListBox lbExperiment2 = new ListBox();
	private TextArea taDescription2 = new TextArea();
	private TextArea taNotes2 = new TextArea();
	private DateBox dbFrom2 = new DateBox();
	private DateBox dbTo2 = new DateBox();
	private Button btGenerate2 = new Button("Generate");

	@Override
	public void continueModuleLoad()
	{

		showDialogWait();
		// Get all controllers
		entityService.getEntity(new ControllerDTO(), null, new AsyncCallback<List<ControllerDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<ControllerDTO> result)
			{
				dbWait.hide();
				CONTROLLERS = result;
				lbController.addItem("Select...");
				lbController2.addItem("Select...");

				for (ControllerDTO c : CONTROLLERS)
				{
					lbController.addItem(c.getName(), c.getId() + "");
					lbController2.addItem(c.getName(), c.getId() + "");
				}
			}
		});

		// Get all sensors's types
		entityService.getSensorType(new AsyncCallback<List<SensorTypeDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				dbWait.hide();
				// TODO:
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<SensorTypeDTO> result)
			{
				for (SensorTypeDTO c : result)
				{
					// Name ID
					lbTypeSensorAll.addItem(c.getName(), (c.getId() + ""));
				}

			}
		});

		// Experiment 1

		tableData.setText(0, 0, "Controller: ");
		tableData.setWidget(0, 1, lbController);

		tableData.setText(1, 0, "SmartThing: ");
		tableData.setWidget(1, 1, lbSmartThing);

		tableData.setText(2, 0, "Experiment: ");
		tableData.setWidget(2, 1, lbExperiment);

		tableData.setText(3, 0, "Description: ");
		tableData.setWidget(3, 1, taDescription);

		tableData.setText(4, 0, "Notes: ");
		tableData.setWidget(4, 1, taNotes);

		tableData.setText(5, 0, "Type sensor: ");
		tableData.setWidget(5, 1, lbTypeSensor);

		tableData.setText(6, 0, "From date: ");
		tableData.setWidget(6, 1, dbFrom);

		tableData.setText(7, 0, "To date: ");
		tableData.setWidget(7, 1, dbTo);

		DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat("MMMM dd yyyy"));
		dbFrom.setFormat(format);
		dbTo.setFormat(format);
		dbFrom.getDatePicker().setYearArrowsVisible(true);
		dbTo.getDatePicker().setYearArrowsVisible(true);

		dbFrom.setEnabled(false);
		dbTo.setEnabled(false);
		lbSmartThing.setEnabled(false);
		lbSensor.setEnabled(false);

		VerticalPanel v1 = new VerticalPanel();
		Label l1 = new Label("Statistics from ...");
		l1.setStyleName("lbProperty");
		v1.add(l1);
		v1.add(tableData);
		v1.add(btGenerate);
		v1.setCellHorizontalAlignment(btGenerate, HasHorizontalAlignment.ALIGN_RIGHT);

		DecoratorPanel d1 = new DecoratorPanel();
		d1.add(v1);

		// Experiment 2

		tableData2.setText(0, 0, "Controller: ");
		tableData2.setWidget(0, 1, lbController2);

		tableData2.setText(1, 0, "SmartThing: ");
		tableData2.setWidget(1, 1, lbSmartThing2);

		tableData2.setText(2, 0, "Experiment: ");
		tableData2.setWidget(2, 1, lbExperiment2);

		tableData2.setText(3, 0, "Description: ");
		tableData2.setWidget(3, 1, taDescription2);

		tableData2.setText(4, 0, "Notes: ");
		tableData2.setWidget(4, 1, taNotes2);

		tableData2.setText(5, 0, "Type sensor: ");
		tableData2.setWidget(5, 1, lbTypeSensor2);

		tableData2.setText(6, 0, "From date: ");
		tableData2.setWidget(6, 1, dbFrom2);

		tableData2.setText(7, 0, "To date: ");
		tableData2.setWidget(7, 1, dbTo2);

		dbFrom2.setFormat(format);
		dbTo2.setFormat(format);
		dbFrom2.getDatePicker().setYearArrowsVisible(true);
		dbTo2.getDatePicker().setYearArrowsVisible(true);

		dbFrom2.setEnabled(false);
		dbTo2.setEnabled(false);
		lbSmartThing2.setEnabled(false);
		lbSensor2.setEnabled(false);

		VerticalPanel v2 = new VerticalPanel();
		Label l2 = new Label("Statistics from ...");
		l2.setStyleName("lbProperty");
		v2.add(l2);
		v2.add(tableData2);
		v2.add(btGenerate2);
		v2.setCellHorizontalAlignment(btGenerate2, HasHorizontalAlignment.ALIGN_RIGHT);

		DecoratorPanel d2 = new DecoratorPanel();
		d2.add(v2);

		// //////////////////////////////////////////////////////////

		HorizontalPanel eAll = new HorizontalPanel();
		eAll.setSpacing(10);
		eAll.add(d1);
		eAll.add(d2);

		formPanel.add(eAll);
		RootPanel.get("formContainer").add(formPanel);

		// Experiment 1
		fillExperiment1();

		// Experiment 2
		fillExperiment2();
	}

	public void fillExperiment1()
	{
		lbController.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event)
			{
				final int idController = Integer.parseInt(lbController.getValue(lbController.getSelectedIndex()));
				entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SmartThingDTO> result)
					{

						SMARTTHINGS = result;
						lbSmartThing.clear();
						lbSmartThing.addItem("Select...");
						lbSmartThing.setEnabled(true);
						for (SmartThingDTO c : SMARTTHINGS)
						{
							if (c.getIdcontroller() == idController)
							{
								lbSmartThing.addItem(c.getName(), c.getId() + "");
							}
						}

					}
				});

			}
		});

		lbSmartThing.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event)
			{

				final int idSmartThing = Integer.parseInt(lbSmartThing.getValue(lbSmartThing.getSelectedIndex()));

				entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SensorDTO> result)
					{
						SENSORS = result;
						lbSensor.clear();

						lbTypeSensor.clear();
						lbSensor.setEnabled(true);

						for (SensorDTO c : SENSORS)
						{
							measure_unit = c.getUnit();
							if (c.getIdthing() == idSmartThing)
							{
								// Name Id
								lbSensor.addItem(getSensor_typeName(c.getSensor_type()), c.getSensor_type());

							}
						}

						ArrayList<String> typeSensor = new ArrayList<String>();

						for (int i = 0; i < lbSensor.getItemCount(); i++)
						{
							if (!typeSensor.contains(lbSensor.getValue(i)))
							{
								typeSensor.add(lbSensor.getValue(i));
								// Name Id
								lbTypeSensor.addItem(lbSensor.getItemText(i), lbSensor.getValue(i));
							}
						}
					}
				});

				entityService.getEntity(new ExperimentDTO(), null, new AsyncCallback<List<ExperimentDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ExperimentDTO> result)
					{
						EXPERIMENT = result;
						lbExperiment.clear();

						lbExperiment.addItem("Select..", "-1");
						lbExperiment.addItem("None", "-1");

						for (ExperimentDTO c : EXPERIMENT)
						{
							if (c.getIdthing() == idSmartThing)
							{
								// Name Id
								String label = c.getName() + " : " + c.getDescription();
								lbExperiment.addItem(label, c.getId() + "");

							}
						}
					}
				});
			}
		});

		lbExperiment.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event)
			{
				final int idExperiment = Integer.parseInt(lbExperiment.getValue(lbExperiment.getSelectedIndex()));

				if (idExperiment == -1)
				{
					dbFrom.setEnabled(true);
					dbTo.setEnabled(true);

					taDescription.setText("");
					taNotes.setText("");
				}
				else
				{

					entityService.getEntity(new ExperimentDTO(), idExperiment,
							new AsyncCallback<List<ExperimentDTO>>() {

								@Override
								public void onFailure(Throwable caught)
								{
									// TODO Auto-generated method stub
								}

								@Override
								public void onSuccess(List<ExperimentDTO> result)
								{

									dbTo.setValue(result.get(0).getEnd_date());
									dbFrom.setValue(result.get(0).getStart_date());
									dbFrom.setEnabled(false);
									dbTo.setEnabled(false);

									taDescription.setText(result.get(0).getDescription());
									taNotes.setText(result.get(0).getNotes());
								}
							});
				}
			}
		});

		btGenerate.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{
				dbWait.show();

				GraphUtils.hideNVD3(1);
				group = new HashMap<String, List<MeasureDTO>>();
				formChart.clear();
				formPictures.clear();
				final String type = lbTypeSensor.getValue(lbTypeSensor.getSelectedIndex());
				int s = 0;

				for (int i = 0; i < SENSORS.size(); i++)
				{
					if (SENSORS.get(i).getSensor_type().equals(type))
					{
						s++;
					}
				}

				final int sf = s;

				for (int i = 0; i < SENSORS.size(); i++)
				{
					if (SENSORS.get(i).getSensor_type().equals(type))
					{
						final String name = SENSORS.get(i).getName();
						entityService.getSensorData(SENSORS.get(i).getId(), dbFrom.getValue(), dbTo.getValue(),
								new AsyncCallback<List<MeasureDTO>>() {

									@Override
									public void onFailure(Throwable caught)
									{
										dbWait.hide();

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
												Grid grid = new Grid(numRows + 1, numColumns);
												grid.setCellPadding(3);
												int i = 0;

												for (int row = 0; row <= numRows; row++)
												{
													for (int col = 0; col < numColumns; col++, i++)
													{
														if (i < result.size())
														{
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
															imagePopup2.setText(result.get(i).getMeasure_date() + "");
															imagePopup2.setWidget(imageFull);

															imageFull.addClickHandler(new ClickHandler() {
																public void onClick(ClickEvent event)
																{
																	imagePopup2.hide();
																}
															});

															image.addClickHandler(new ClickHandler() {
																public void onClick(ClickEvent event)
																{
																	imagePopup2.center();
																	imagePopup2.show();
																}
															});
															grid.setWidget(row, col, image);
														}
														else
														{
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
										}
										else
										{
											group.put(name, null);
										}

										// last result has arrived, process data
										if (group.size() == sf)
										{
											String data = GraphUtils.generateStringData(group);
											if (data == null)
											{

												final DialogBox noDatas = new DialogBox();
												noDatas.setAnimationEnabled(true);
												noDatas.setGlassEnabled(true);
												noDatas.setText("There is no data for display.");
												Button close = new Button("Close");
												close.addClickHandler(new ClickHandler() {
													public void onClick(ClickEvent event)
													{
														noDatas.hide();
													}
												});
												noDatas.setWidget(close);
												noDatas.show();
												noDatas.center();
											}
											else
											{
												GraphUtils.generateNVD3(measure_unit, "", data, 1);
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

	public void fillExperiment2()
	{
		lbController2.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event)
			{

				final int idController2 = Integer.parseInt(lbController2.getValue(lbController2.getSelectedIndex()));

				entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SmartThingDTO> result)
					{

						SMARTTHINGS = result;
						lbSmartThing2.clear();

						lbSmartThing2.addItem("Select...");

						lbSmartThing2.setEnabled(true);

						for (SmartThingDTO c : SMARTTHINGS)
						{
							if (c.getIdcontroller() == idController2)
							{
								lbSmartThing2.addItem(c.getName(), c.getId() + "");
							}
						}

					}
				});

			}
		});

		lbSmartThing2.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event)
			{

				final int idSmartThing = Integer.parseInt(lbSmartThing2.getValue(lbSmartThing2.getSelectedIndex()));

				entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SensorDTO> result)
					{
						SENSORS = result;
						lbSensor2.clear();

						lbTypeSensor2.clear();
						lbSensor2.setEnabled(true);

						for (SensorDTO c : SENSORS)
						{
							measure_unit = c.getUnit();
							if (c.getIdthing() == idSmartThing)
							{
								// Name Id
								lbSensor2.addItem(getSensor_typeName(c.getSensor_type()), c.getSensor_type());

							}
						}

						ArrayList<String> typeSensor = new ArrayList<String>();

						for (int i = 0; i < lbSensor2.getItemCount(); i++)
						{
							if (!typeSensor.contains(lbSensor2.getValue(i)))
							{
								typeSensor.add(lbSensor2.getValue(i));
								// Name Id
								lbTypeSensor2.addItem(lbSensor2.getItemText(i), lbSensor2.getValue(i));
							}
						}
					}
				});

				entityService.getEntity(new ExperimentDTO(), null, new AsyncCallback<List<ExperimentDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ExperimentDTO> result)
					{
						EXPERIMENT = result;
						lbExperiment2.clear();

						lbExperiment2.addItem("Select..", "-1");
						lbExperiment2.addItem("None", "-1");

						for (ExperimentDTO c : EXPERIMENT)
						{
							if (c.getIdthing() == idSmartThing)
							{
								// Name Id
								String label = c.getName() + " : " + c.getDescription();
								lbExperiment2.addItem(label, c.getId() + "");

							}
						}
					}
				});
			}
		});

		lbExperiment2.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event)
			{
				final int idExperiment = Integer.parseInt(lbExperiment2.getValue(lbExperiment2.getSelectedIndex()));

				if (idExperiment == -1)
				{
					dbFrom2.setEnabled(true);
					dbTo2.setEnabled(true);

					taDescription2.setText("");
					taNotes2.setText("");
				}
				else
				{

					entityService.getEntity(new ExperimentDTO(), idExperiment,
							new AsyncCallback<List<ExperimentDTO>>() {

								@Override
								public void onFailure(Throwable caught)
								{
									// TODO Auto-generated method stub
								}

								@Override
								public void onSuccess(List<ExperimentDTO> result)
								{

									dbTo2.setValue(result.get(0).getEnd_date());
									Window.alert(result.get(0).getEnd_date() + "");
									dbFrom2.setValue(result.get(0).getStart_date());

									dbFrom2.setEnabled(false);
									dbTo2.setEnabled(false);

									taDescription2.setText(result.get(0).getDescription());
									taNotes2.setText(result.get(0).getNotes());
								}
							});
				}
			}
		});

		btGenerate2.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{

			}
		});

	}

	public String getSensor_typeName(String id)
	{
		String name = "";

		for (int i = 0; i < lbTypeSensorAll.getItemCount(); i++)
		{
			if (lbTypeSensorAll.getValue(i).equals(id))
			{
				name = lbTypeSensorAll.getItemText(i);
			}
		}

		return name;
	}

	public void showDialogWait()
	{

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
