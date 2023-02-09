<template>
  <div id='quote-changer'>
    <p id="inspo">{{inspirationalQuote}}</p>
  </div>
</template>

<script>
export default {
    data() {
        return {
            quotes: [
                "\"What seems impossible today will one day become your warm-up.\"", 
                "\"If it doesn't challenge you,\nit doesn't change you.\"", 
                "\"The only place where success comes before work is in the dictionary.\"",
                "\"If something stands between you and your success, move it. Never be denied.\"","\"Motivation is what gets you started.\nHabit is what keeps you going.\"",
                "\"The difference between try and triumph\nis a little ‘umph.\"",
                "\"Put all excuses aside and remember this:\nYou are capable.\""]
        }
    },
    computed: {
        inspirationalQuote() {
            return this.quotes[Math.floor(Math.random() * Math.floor(this.quotes.length))];
        }
    },
    mounted: function() {
        this.inspirationalQuote;
    }
}
</script>

<style scoped>
#inspo {
  font-size: 20px;
  text-align: center;
  width: 370px;
  margin: 10px auto;
}
</style>