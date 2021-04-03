<template>
  <DebateSummary></DebateSummary>
  <DebateStatistics></DebateStatistics>
  <DebateOpinion></DebateOpinion>
  <WriteReply></WriteReply>

</template>

<script>
export default {
  name: "ReadDebateBoard",
  components: {
    DebateSummary: import("@/components/DebateSummary"),
    DebateStatistics: import("@/components/DebateSummary"),
    DebateOpinion: import("@/components/DebateOpinion"),
    WriteReply: import("@/components/WriteReply")
  }
}
</script>

<style scoped>

</style>