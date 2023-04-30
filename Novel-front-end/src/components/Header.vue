<template>
  <div class="header">
    <Top />
    <Navbar />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar";
import Top from "@/components/Top";
export default {
  name: "Header",
  components: {
    Navbar,
    Top,
  },
};
</script>