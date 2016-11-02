// #1: document ready
$(document).ready(function () {
    drawChart();
});
// #2: draw the bar chart
function drawChart() {
    // #3: dummy data - will be replaced with Ajax call
    var data = google.visualization.arrayToDataTable([
        ['Director', '# Films'],
        ['Tarantino', 8],
        ['Coen Bros', 9],
        ['Sono', 4],
        ['Miyazaki', 7]
    ]);
    // #4: this sets up the size of the chart, the main title, and the axis titles
    var options = {
        title: 'Movies By Director',
        vAxis: {title: 'Director', titleTextStyle: {color: 'red'}},
        hAxis: {title: 'Num Movies', titleTextStyle: {color: 'red'}},
        'width': 500,
        'height': 400
    };
    // #5: create a new BarChart object, handing it the div into which we want it to render
    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    // #6: tell the chart to draw itself
    chart.draw(data, options);
}
;