function plotarGrafico(id, field){
  const ctx = document.getElementById(`${field}-totem-${id}`);

  const labels = ["2013", "2014", "2015", "2016", "2017", "2018"];
  
  const data = {
    labels,
    datasets: [
      {
        data: [211, 299, 189, 411, 377, 421],
        label: "Progressão de vendas",
      },
    ],
  };
  
  const config = {
    type: "line",
    data,
    options: {
      responsive: true,
    },
  };
  
  const myChart = new Chart(ctx, config);
}
