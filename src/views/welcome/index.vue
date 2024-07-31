<template>
  <h1>{{ res }}</h1>
</template>

<script setup>
import { http } from "@/utils/http";
import { ref } from "vue";

const res = ref();
// params传参
const textRequest = async () => {
  const response = await http.request("get", "/api");
  res.value = response.data;
};
textRequest();
</script>
