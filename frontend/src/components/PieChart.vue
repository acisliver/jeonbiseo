<script>
import { Pie } from "vue-chartjs";

export default {
  extends: Pie,
  props: {
    chartDataObj: {
      type: Object,
      required: true
    }
  },
  mounted() {
    this.gradient = this.$refs.canvas
        .getContext("2d")
        .createLinearGradient(0, 0, 0, 450);
    this.gradient2 = this.$refs.canvas
        .getContext("2d")
        .createLinearGradient(0, 0, 0, 450);

    this.gradient.addColorStop(0, "rgb(222,13,13)");
    this.gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");

    this.gradient2.addColorStop(0, "rgb(0,94,255)");
    this.gradient2.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
    this.renderChart(
        {
          labels: ["찬성", "반대"],
          datasets: [
            {
              backgroundColor: [this.gradient2, this.gradient],
              data: [this.chartDataObj.pros, this.chartDataObj.cons]
            }
          ]
        },
        { responsive: true, maintainAspectRatio: false }
    );
  }
};
</script>