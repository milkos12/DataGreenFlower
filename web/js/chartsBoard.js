
function sd() {

}
function sdf() {

}
let confirm = false
let ctx = document.getElementById('myChart').getContext('2d');
function srd(headers, values) {
    if (confirm) {
        myChart.destroy();
    }
    confirm = true;

    ctx = document.getElementById('myChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: headers,
            datasets: [{
                    label: '# of Votes',
                    data: values,
                    backgroundColor: [
                        'rgba(0, 210, 146)',
                        'rgba(200, 53, 69)',
                        'rgba(255, 206, 86)',
                        'rgba(75, 192, 192)',
                        'rgba(153, 102, 255)',
                        'rgba(255, 159, 64)',
                        'rgba(255, 159, 53)'
                    ],
                    borderColor: [
                        'rgba(0, 210, 146)',
                        'rgba(200, 53, 69)',
                        'rgba(255, 206, 86)',
                        'rgba(75, 192, 192)',
                        'rgba(153, 102, 255)',
                        'rgba(255, 159, 64)',
                        'rgba(255, 159, 53)'
                    ],

                }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                    labels: {
                        // This more specific font property overrides the global property
                        font: {
                            size: 10
                        }
                    }
                },
                title: {
                    display: true,
                    text: ''
                }
            }
        }
    });

}







