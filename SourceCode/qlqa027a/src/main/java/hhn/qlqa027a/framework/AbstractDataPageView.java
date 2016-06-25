/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */

package hhn.qlqa027a.framework;

import hhn.qlqa027a.entities.BaseEntity;
import hhn.qlqa027a.component.JSearchField;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTableHeader;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.painter.decoration.DecorationAreaType;

/**
 * Abstract Page View 
 * 
 * @param <T> entity
 * 
 * @author Cem Ikta
 */
public abstract class AbstractDataPageView<T extends BaseEntity> implements DataPageView<T> {

    protected DataPageController<T> controller;
    protected JComponent dataPageView = null;
    protected JSplitPane splitPane;
    private AbstractPreviewPanel previewPanel = null;

    // default page actions
    private Action acAddNew;
    private Action acEdit;
    private Action acDelete;
    private Action acRefresh;
    private Action acSearch;
    protected JSearchField searchField;
    
    // jxtable
    protected JXTable xtable;
    protected JXTableHeader xtableHeader;
    private EntityTableModel tableModel;
    
    // pager actions
    private Action acFirstPage;
    private Action acPreviousPage;
    private Action acNextPage;
    private Action acLastPage;
    
    private JLabel lblCurrentPage;
    private JLabel lblFrom;
    private JLabel lblPageCount;
    private JLabel lblRecordsFound;
    private int currentPage = 0;
    private int rowCount = 0;
    private int pageCount = 0;
    private final int pageSize = 20;
    
    protected String searchFilter = "";

    /**
     * Creates a new instance
     */
    public AbstractDataPageView() {
    }

    /**
     * init 
     * 
     * @param controller data page controller
     */
    public void init(DataPageController<T> controller) {
        this.controller = controller;
        initComponents();
    }

    /**
     * init components
     */
    private void initComponents() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.add(getHeaderBar(), BorderLayout.NORTH);
        viewPanel.add(getTablePanel(), BorderLayout.CENTER);

        dataPageView = viewPanel;
        
        // preview panel
        previewPanel = getPreviewPanel();
        if (previewPanel != null) {
            splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            splitPane.setDividerSize(5);
            splitPane.setResizeWeight(0.7);
            splitPane.setTopComponent(viewPanel);
            splitPane.setBottomComponent(previewPanel);

            viewPanel.setMinimumSize(new Dimension(300, 300));
            previewPanel.setMinimumSize(new Dimension(220, 220));

            dataPageView = splitPane;
        }

        setCurrentPage(1);
    }
    
    /**
     * HeaderBar of data page view
     *
     * @return header bar
     */
    public JPanel getHeaderBar() {
        JPanel headerBar = new JPanel(new MigLayout("insets 2 2 2 2"));

        JLabel lblTitle = new JLabel(getTitle());
        lblTitle.setIcon(new ImageIcon(getClass().getResource(getIconPath())));
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 14));

        // JSearchField
        acSearch = new AbstractAction("search") {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setTableFilter();
            }
        };
        searchField = new JSearchField(acSearch);
        
        // toolbar
        JToolBar tbHeader = new JToolBar();
        tbHeader.setFloatable(false);
        tbHeader.setRollover(true);
        tbHeader.setFocusable(false);
        buildHeaderBarActions();
        
        tbHeader.add(ViewHelpers.createToolButton(acAddNew, false, false));
        tbHeader.add(ViewHelpers.createToolButton(acEdit, false, false));
        tbHeader.add(ViewHelpers.createToolButton(acDelete, false, false));
        tbHeader.add(ViewHelpers.createToolButton(acRefresh, false, false));

        headerBar.setPreferredSize(new Dimension(searchField.getWidth(), 
                searchField.getHeight() + 28));
        headerBar.add(lblTitle, "dock center, gapleft 4");
        headerBar.add(searchField, "dock east, gapright 4, width 250!, height pref!");
        headerBar.add(tbHeader, "dock east, gapright 10, width 250!, height pref!");

        SubstanceLookAndFeel.setDecorationType(headerBar, DecorationAreaType.HEADER);
        SubstanceLookAndFeel.setDecorationType(tbHeader, DecorationAreaType.HEADER);
        SubstanceLookAndFeel.setDecorationType(searchField, DecorationAreaType.GENERAL);

        return headerBar;
    }

    /**
     * JXTable settings for PageView.
     *
     * @return Panel with JXTable and Paging.
     */
    public JPanel getTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());

        xtable = new JXTable();
        xtable.setRowHeight(19);
        
        xtable.addMouseListener(new MouseAdapter() { 
            @Override
            public void mousePressed(MouseEvent me) { 
                if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() > 1) {
                    controller.onEdit();
                }
            } 
        });        
        
        xtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        xtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                if (previewPanel != null) {
                    notifyPreviewPanel();
                }
            }
        });
        xtable.setColumnControlVisible(true);
        xtable.setHighlighters(HighlighterFactory.createSimpleStriping(
                HighlighterFactory.BEIGE));
        xtable.getTableHeader().setPreferredSize(
                new Dimension(xtable.getTableHeader().getPreferredSize().width, 19));

        // table model and build columns
        tableModel = new EntityTableModel();
        addTableColumns();
        xtable.setModel(tableModel);
        
        // xtable AutoResizeMode
        xtable.setAutoResizeMode(getJXTableAutoResizeMode());

        // toolbar
        JToolBar tbPager = new JToolBar();
        tbPager.setFloatable(false);
        tbPager.setRollover(true);
        tbPager.setFocusable(false);
        SubstanceLookAndFeel.setDecorationType(tbPager, DecorationAreaType.GENERAL);
        
        buildPagerActions();
        lblCurrentPage = new JLabel();
        lblFrom = new JLabel(I18n.COMMON.getString("AbstractPageView.Pager.From") + " ");
        lblPageCount = new JLabel();
        lblRecordsFound = new JLabel();
        
        tbPager.add(ViewHelpers.createToolButton(acFirstPage, false, false));
        tbPager.add(ViewHelpers.createToolButton(acPreviousPage, false, false));
        tbPager.add(new JLabel(" "));
        tbPager.add(lblCurrentPage);
        tbPager.add(lblFrom);
        tbPager.add(lblPageCount);
        tbPager.add(new JLabel(" "));
        tbPager.add(ViewHelpers.createToolButton(acNextPage, false, false));
        tbPager.add(ViewHelpers.createToolButton(acLastPage, false, false));
        tbPager.addSeparator();
        tbPager.add(Box.createGlue());
        tbPager.add(lblRecordsFound);

        tablePanel.add(new JScrollPane(xtable), BorderLayout.CENTER);
        tablePanel.add(tbPager, BorderLayout.SOUTH);

        // xtable columns settings
        setUpColumns();

        return tablePanel;
    }
    
    /**
     * Gets table model of JXTable
     * 
     * @return table model
     */
    public EntityTableModel getTableModel() {
        return tableModel;
    }

    /**
     * If predataPageView panel exist, override this method.
     *
     * @return predataPageView panel
     */
    public AbstractPreviewPanel getPreviewPanel() {
        return null;
    }

    /**
     * If JXTable selected object changed, sets dataPageView panel data object, and
     * predataPageView panel shows new data values
     */
    public void notifyPreviewPanel() {
        if (xtable.getSelectedRow() == -1) {
            return;
        }
        previewPanel.setEntity(getSelectedModel());
    }

    /**
     * AbstractDataPageView component with HeaderBar, TablePanel and PredataPageViewPanel.
     * 
     * @return dataPageView as dataPageView component 
     */
    @Override
    public Component asComponent() {
        return dataPageView;
    }

    /**
     * Add table columns.
     */
    public abstract void addTableColumns();

    /**
     * Gets JXTable auto resize mode
     * 
     * @return auto resize mode for JXTable
     */
    public int getJXTableAutoResizeMode() {
        return JXTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS;
    }

    /**
     * Refresh data for PageView's JXTable
     */
    @Override
    public void refreshData() {
        int start = ((pageSize * getCurrentPage()) - pageSize);
	int end = start + pageSize;
        
        tableModel.setData(getController().getData(searchFilter, start, end));
        tableModel.fireTableDataChanged();

        setPageCount();
        setNavigationRecordCount();

        if (tableModel.getRowCount() > 0) {
            xtable.setRowSelectionInterval(0, 0);
        }
    }

    /**
     * Sets table filter for JXTable
     */
    private void setTableFilter() {
        searchFilter = searchField.getSearchTextField().getText();
        setCurrentPage(1);
        refreshData();
    }
    
    /**
     * Gets search filter
     * 
     * @return search filter
     */
    public String getSearchFilter() {
        return searchFilter;
    }

    /**
     * Sets search filter
     * 
     * @param searchFilter search filter 
     */
    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    /**
     * First page for JXTable paging.
     */
    public void firstPage() {
        if (getCurrentPage() > 1) {
            setCurrentPage(1);
            refreshData();
        }
    }

    /**
     * Previous page for JXTable paging.
     */
    public void previousPage() {
        if (getCurrentPage() > 1) {
            setCurrentPage(getCurrentPage() - 1);
            refreshData();
        }
    }

    /**
     * Next page for JXTable paging.
     */
    public void nextPage() {
        if (getCurrentPage() < getPageCount()) {
            setCurrentPage(getCurrentPage() + 1);
            refreshData();
        }
    }

    /**
     * Last page for JXTable paging.
     */
    public void lastPage() {
        if (getCurrentPage() < getPageCount()) {
            setCurrentPage(getPageCount());
            refreshData();
        }
    }

    /**
     * Sets current page for JXTable
     *
     * @param currentPage for JXTable
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        lblCurrentPage.setText(
                I18n.COMMON.getString("AbstractPageView.Pager.CurrentPage") 
                        + " " + String.valueOf(this.currentPage));
    }

    /**
     * Gets current page of JXTable
     *
     * @return currentPage of JXTable
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     * Sets page count for JXTable.
     */
    public void setPageCount() {
        rowCount = (int) Math.ceil((double) (getController().getDataSize(searchFilter)));
        pageCount = (int) Math.ceil((double) (rowCount / pageSize));
        double remainder = (rowCount % pageSize);

        if (remainder > 0) {
            pageCount += 1;
        }
        lblPageCount.setText(String.valueOf(pageCount));
    }

    /**
     * Gets page count for JXTable
     *
     * @return pageCount for JXTable
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * Gets row count for JXTable
     *
     * @return rowCount for JXTable
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Sets navigation recourd count. 
     * This is changed record count after table filtering.
     */
    private void setNavigationRecordCount() {
        if (getRowCount() > 1) {
            lblRecordsFound.setText(" " + String.valueOf(getRowCount()) 
                    + " " + I18n.COMMON.getString("AbstractPageView.Pager.RecordsFound") + " ");
        } else {
            lblRecordsFound.setText(" " + String.valueOf(getRowCount()) 
                    + " " + I18n.COMMON.getString("AbstractPageView.Pager.RecordFound") + " ");
        }
    }

    /**
     * Sets JXTable column preferredWidth and visible settings
     */
    public void setUpColumns() {
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            xtable.getColumnExt(tableModel.getColumnName(i)).setPreferredWidth(
                    tableModel.getColumn(i).getWidth());
            xtable.getColumnExt(tableModel.getColumnName(i)).setVisible(
                    tableModel.getColumn(i).isVisible());

            if (xtable.getColumnExt(tableModel.getColumnName(i)).isVisible()) {
                xtable.moveColumn(xtable.convertColumnIndexToView(i), i);
            }
        }
    }

    /**
     * Gets controller of page dataPageView
     *
     * @return controller of page dataPageView
     */
    @Override
    public DataPageController<T> getController() {
        return this.controller;
    }

    /**
     * Gets selected entity model of JXTable.
     *
     * @return JXTable selected object
     */
    @Override
    public T getSelectedModel() {
        if (xtable.getSelectedRow() > -1) {
            return (T)tableModel.getRowAt(xtable.convertRowIndexToModel(
                    xtable.getSelectedRow()));
        } else {
            return null;
        }
    }

    /**
     * Builds header bar actions
     */
    private void buildHeaderBarActions() {
        acAddNew = new AbstractAction(I18n.COMMON.getString("Action.AddNew"), 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "addnew.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().onAddNew();
            }
        };
        acAddNew.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.AddNew"));
        
        acEdit = new AbstractAction(I18n.COMMON.getString("Action.Edit"), 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "edit.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().onEdit();
            }
        };
        acEdit.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.Edit"));
        
        acDelete = new AbstractAction(I18n.COMMON.getString("Action.Delete"), 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "delete.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().onDelete();
            }
        };
        acDelete.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.Delete"));
        
        acRefresh = new AbstractAction(I18n.COMMON.getString("Action.Refresh"), 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "refresh.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        };
        acRefresh.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.Refresh"));
    }
    
    /**
     * Builds pager toolbar actions
     */
    private void buildPagerActions() {
        acFirstPage = new AbstractAction("firstPage", 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "first.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPage();
            }
        };
        acFirstPage.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.FirstPage"));
        
        acPreviousPage = new AbstractAction("previousPage", 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "previous.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage();
            }
        };
        acPreviousPage.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.PreviousPage"));

        acNextPage = new AbstractAction("nextPage", 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "next.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
            }
        };
        acNextPage.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.NextPage"));

        acLastPage = new AbstractAction("lastPage", 
            new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "last.png"))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage();
            }
        };
        acLastPage.putValue(Action.SHORT_DESCRIPTION, I18n.COMMON.getString("Action.Hint.LastPage"));
    }
    
}
