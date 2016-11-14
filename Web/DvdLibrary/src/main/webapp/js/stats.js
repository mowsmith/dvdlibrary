// #1: document ready
$(document).ready(function () {
    drawChart();
});
// #2: draw the bar chart
// draw the bar chart
function drawChart() {
    $.get('stats/chart').success(function (data) {
        var dataTable = new google.visualization.DataTable(data);
        var options = {
            title: 'Movies by Director',
            vAxis: {title: 'Director', titleTextStyle: {color: 'red'}},
            hAxis: {title: 'Num Movies', titleTextStyle: {color: 'red'}}, 'width': 500,
            'height': 400
        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(dataTable, options);
    });
}