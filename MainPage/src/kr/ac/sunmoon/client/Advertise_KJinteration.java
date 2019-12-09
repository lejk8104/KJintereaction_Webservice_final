package kr.ac.sunmoon.client;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Legend;
import org.moxieapps.gwt.highcharts.client.Point;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.ToolTip;
import org.moxieapps.gwt.highcharts.client.ToolTipData;
import org.moxieapps.gwt.highcharts.client.ToolTipFormatter;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsFormatter;
import org.moxieapps.gwt.highcharts.client.labels.PieDataLabels;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.VerticalLayout;


public class Advertise_KJinteration extends Panel{

	private String [] kjinteractioncitylist = new String[16];
	public static String[][] highchart;
	
	public Advertise_KJinteration() {
		// TODO Auto-generated constructor stub
		super();
		this.setBorder(false);
		this.setPaddings(0);
		this.setClosable(true);
		this.setWidth(950);
		this.setHeight(700);
		this.setMargins(10, 55, 100, 0);

		Panel formpanel = new Panel();
		formpanel.setFrame(true);
		formpanel.setLayout(new VerticalLayout(10));
		formpanel.setWidth(950);
		formpanel.setHeight(700);
	    formpanel.setMargins(20, 135, 100, 20);
	    
//	    KJMembershipServiceAsync service = GWT.create(KJMembershipService.class);
//        service.fetchDataByLocal(new AsyncCallback<String[][]>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				com.google.gwt.user.client.Window.alert("failed with connection");
//			}
//
//			@Override
//			public void onSuccess(String[][] result) {
//				// TODO Auto-generated method stub
//				com.google.gwt.user.client.Window.alert("succeeded in connection");
//				highchart = result;
//			}
//        });
		
		Chart chart = createChart();
		
		Label adlbl = new Label("A lot of people are participating in kJ interaction\r\n" + 
				"If you lead to change, come on join us!!!");
		formpanel.add(chart);
		formpanel.add(adlbl);
	    this.add(formpanel);
	}
	public Chart createChart() {  
		
	        final Chart chart = new Chart()  
	            .setType(Series.Type.PIE)  
	            .setChartTitleText("Statistics city of low KJ Interaction")  
//	            .setPlotBackgroundColor((String) null)  //�׶��̼�
	            .setPlotBorderWidth(null)  
	            .setPlotShadow(true)  
	            .setPiePlotOptions(new PiePlotOptions()  
	                .setAllowPointSelect(true)  
	                .setCursor(PlotOptions.Cursor.POINTER)  
	                .setPieDataLabels(new PieDataLabels()  
	                    .setConnectorColor("#000000")  
	                    .setEnabled(true)  
	                    .setColor("#000000")  
	                    .setFormatter(new DataLabelsFormatter() {  
	                        public String format(DataLabelsData dataLabelsData) {  
	                            return "<b>" + dataLabelsData.getPointName() + "</b>: " + dataLabelsData.getYAsDouble() + " %";  
	                        }  
	                    })  
	                )  
	            )  
	            .setLegend(new Legend()  
	                .setLayout(Legend.Layout.VERTICAL)  
	                .setAlign(Legend.Align.RIGHT)  
	                .setVerticalAlign(Legend.VerticalAlign.TOP)  
	                .setX(-100)  
	                .setY(100)  
	                .setFloating(true)  
	                .setBorderWidth(1)  
	                .setBackgroundColor("#FFFFFF")  
	                .setShadow(true)  
	            )  
	            .setToolTip(new ToolTip()  
	                .setFormatter(new ToolTipFormatter() {  
	                    public String format(ToolTipData toolTipData) {  
	                        return "<b>" + toolTipData.getPointName() + "</b>: " + toolTipData.getYAsDouble() + " %";  
	                    }  
	                })  
	            );  
	        
	        chart.addSeries(chart.createSeries()  
	            .setName("Low KJ Interaction")  
	            .setPoints(new Point[]{  
            		new Point(highchart[0][0], Double.parseDouble(highchart[0][1])),
            		new Point(highchart[1][0], Double.parseDouble(highchart[1][1])),
            		new Point(highchart[2][0], Double.parseDouble(highchart[2][1])),
            		new Point(highchart[3][0], Double.parseDouble(highchart[3][1])),
            		new Point(highchart[4][0], Double.parseDouble(highchart[4][1])),
            		new Point(highchart[5][0], Double.parseDouble(highchart[5][1])),
            		new Point("else", 100 - (Double.parseDouble(highchart[0][1]) + Double.parseDouble(highchart[1][1]) + Double.parseDouble(highchart[2][1]) +
            				Double.parseDouble(highchart[3][1]) + Double.parseDouble(highchart[4][1]) + Double.parseDouble(highchart[5][1])))
//	                new Point("Chungcheongnam-do", 0.06),  
//	                new Point("Hokkaido", 1.5),  
//	                new Point("Chubu", 3.78),  
//	                new Point("Chugoku", 5.0),  
//	                new Point("Gyeongsangbuk-", 9.5),  
//	                new Point("Chungcheongbuk-", 13.8),
//	                new Point("Seoul", 41.3),
//	                new Point("Gyeonggi", 45.2)
	            })  
	        );  
	        return chart;  
	    }  
}
