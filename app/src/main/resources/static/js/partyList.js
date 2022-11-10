function categoryChange(e) {
  var good_a = ["한식", "분식", "동남아식", "중식", "일식", "양식"];
  var good_b = ["한식", "분식", "동남아식", "중식", "일식", "양식"];
  var good_c = ["한식", "분식", "동남아식", "중식", "일식", "양식"];
  var good_e = ["한식", "분식", "동남아식", "중식", "일식", "양식"];
  var good_e = ["한식", "분식", "동남아식", "중식", "일식", "양식"];
  var target = document.getElementById("good");

  if (e.value == "a") var d = good_a;
  else if (e.value == "b") var d = good_b;
  else if (e.value == "c") var d = good_c;
  else if (e.value == "d") var d = good_e;
  else if (e.value == "e") var d = good_f;

  target.options.length = 0;

  for (x in d) {
    var opt = document.createElement("option");
    opt.value = d[x];
    opt.innerHTML = d[x];
    target.appendChild(opt);
  }

  let partySelect = document.getElementById("partySelect");
  let value = partySelect.options[document.getElementById("partySelect").selectedIndex].value;
  console.log(value);
}