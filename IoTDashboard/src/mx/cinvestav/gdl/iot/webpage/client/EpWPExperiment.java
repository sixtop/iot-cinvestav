package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ExperimentDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class EpWPExperiment extends IoTEntryPoint {

	private DialogBox dbWait = new DialogBox();
	private int index;

	private VerticalPanel formPanel = new VerticalPanel();

	private Button btAddExperimentDTO = new Button("Add Experiment");

	private CellTable<ExperimentDTO> tableExperimentDTO = new CellTable<ExperimentDTO>();
	private ListDataProvider<ExperimentDTO> dataProvider = new ListDataProvider<ExperimentDTO>();
	private List<ExperimentDTO> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<ExperimentDTO> EXPERIMENT;

	private DialogBox dialogBox = new DialogBox();
	//private Label lbDialogBox = new Label();
	private Button btYes = new Button("Yes");
	private Button btNo = new Button("No");
	//private VerticalPanel dialogPanel = new VerticalPanel();

	@Override
	public void continueModuleLoad() {
		// super.onModuleLoad();
		showDialogWait();
		entityService.getEntity(new ExperimentDTO(), null, new AsyncCallback<List<ExperimentDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<ExperimentDTO> result) {
				dbWait.hide();
				EXPERIMENT = result;

				dataProvider.addDataDisplay(tableExperimentDTO);

				list = dataProvider.getList();
				for (ExperimentDTO c : EXPERIMENT) {
					list.add(c);
				}
			}
		});

		TextColumn<ExperimentDTO> idColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getId() + "";
			}
		};
		idColumn.setSortable(true);

		TextColumn<ExperimentDTO> nameColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getName();
			}
		};
		nameColumn.setSortable(true);

		TextColumn<ExperimentDTO> descriptionColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getDescription();
			}
		};

		TextColumn<ExperimentDTO> notesColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getNotes();
			}
		};
		
		TextColumn<ExperimentDTO> startColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getStart_date()+"";
			}
		};
		startColumn.setSortable(true);
		
		TextColumn<ExperimentDTO> endColumn = new TextColumn<ExperimentDTO>() {
			@Override
			public String getValue(ExperimentDTO c) {
				return c.getEnd_date()+"";
			}
		};
		endColumn.setSortable(true);
	/*	ActionCell<ControllerDTO> editAction = new ActionCell<ControllerDTO>("Edit",
				new ActionCell.Delegate<ControllerDTO>() {
					public void execute(ControllerDTO c) {
						Window.Location.replace("addController.jsp?idController=" + c.getId());
					}
				});

		IdentityColumn<ControllerDTO> editColumn = new IdentityColumn<>(editAction);
		// Column<ControllerDTO, ActionCell<ControllerDTO>> editColumn = identityColumn;

		ActionCell<ControllerDTO> deleteAction = new ActionCell<ControllerDTO>("Delete",
				new ActionCell.Delegate<ControllerDTO>() {
					public void execute(ControllerDTO c) {
						index = c.getId();

						dialogBox.setAnimationEnabled(true);
						dialogBox.setGlassEnabled(true);
						dialogBox.center();

						dialogBox.setText("Warning");

						lbDialogBox.setText("Are you sure to delete the controller? ");
						dialogPanel.add(lbDialogBox);
						dialogPanel.setCellHorizontalAlignment(lbDialogBox, HasHorizontalAlignment.ALIGN_CENTER);

						HorizontalPanel buttonPanel = new HorizontalPanel();
						buttonPanel.add(btYes);
						buttonPanel.add(btNo);

						dialogPanel.add(buttonPanel);
						dialogPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);

						dialogBox.add(dialogPanel);

						dialogBox.show();

					}
				});

		IdentityColumn<ControllerDTO> deleteColumn = new IdentityColumn<>(deleteAction);
*/
		tableExperimentDTO.addColumn(idColumn, "ID");
		tableExperimentDTO.addColumn(nameColumn, "Name");
		tableExperimentDTO.addColumn(descriptionColumn, "Description");
		tableExperimentDTO.addColumn(notesColumn, "Note");
		tableExperimentDTO.addColumn(startColumn, "Start date");
		tableExperimentDTO.addColumn(endColumn, "End date");

		ListHandler<ExperimentDTO> sortHandler = new ListHandler<ExperimentDTO>(dataProvider.getList());
		tableExperimentDTO.addColumnSortHandler(sortHandler);

		sortHandler.setComparator(idColumn, new Comparator<ExperimentDTO>() {
			@Override
			public int compare(ExperimentDTO o1, ExperimentDTO o2) {
				return o1.getId() - o2.getId();
			}
		});

		tableExperimentDTO.getColumnSortList().push(idColumn);

		sortHandler.setComparator(nameColumn, new Comparator<ExperimentDTO>() {
			@Override
			public int compare(ExperimentDTO o1, ExperimentDTO o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		sortHandler.setComparator(startColumn, new Comparator<ExperimentDTO>() {
			@Override
			public int compare(ExperimentDTO o1, ExperimentDTO o2) {
				return o1.getStart_date().compareTo(o2.getStart_date());
			}
		});
		
		sortHandler.setComparator(endColumn, new Comparator<ExperimentDTO>() {
			@Override
			public int compare(ExperimentDTO o1, ExperimentDTO o2) {
				return o1.getEnd_date().compareTo(o2.getEnd_date());
			}
		});

		SimplePager pager = new SimplePager(TextLocation.CENTER, true, true);
		pager.setDisplay(tableExperimentDTO);
		pager.setPageSize(10);

	//	formPanel.add(btAddExperimentDTO);
		formPanel.add(tableExperimentDTO);
		formPanel.setCellHorizontalAlignment(tableExperimentDTO, HasHorizontalAlignment.ALIGN_CENTER);
		formPanel.add(pager);
		formPanel.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);

		RootPanel.get("formContainer").add(formPanel);

		btAddExperimentDTO.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.Location.replace("addExperiment.jsp");
			}
		});

		btYes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				entityService.deleteEntity(new ExperimentDTO(), index, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}

					@Override
					public void onSuccess(Void result) {

					}
				});

				dialogBox.hide();
				Window.Location.reload();

			}
		});

		btNo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
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