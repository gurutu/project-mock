# project-mock
========================================

<b>Project Mock:</b>
<p>It is a UI based application and zero coding skill.It will help you to dump http response.</p>
<b>How to setup:</b>
<ul>
  <li>
    <p> <b>Software needed:-</b> java8,mongoDB</p>
  </li>
  <li>Make jar using cmd: gradlew build -x test</li>
  <li>Run Jar file:-java -jar project-mock-0.0.1-SNAPSHOT</li>
</ul>
<b>How to dump http response:</b>
<p>Run on browser http://localhost:6969</p>
<b>static http response</b>
<table style="width:100%">
  <tr>
    <th>Method</th>
    <th>EndPoint</th>
    <th>Request</th>
     <th>Response</th>
  </tr>
  <tr>
    <td>GET</td>
    <td>/v1/test/4</td>
    <td><code></code></td>
    <td><code>{"Hello":"Hello"}</code></td>
  </tr>
   <tr>
    <td>POST</td>
    <td>/v1/test</td>
    <td><code>{"Hello":"Hello2"}</code></td>
    <td><code>{"Hello":"This is Hello2"}</code></td>
  </tr>
   <tr>
    <td>GET</td>
    <td>/v1/test/1?name=hello</td>
    <td><code></code></td>
    <td><code>{"name":"This is hello"}</code></td>
  </tr>
   <tr>
    <td>POST</td>
    <td>/v1/test?name=hello</td>
    <td><code>{"age":26}</code></td>
    <td><code>{"name":"This is hello","age":26}</code></td>
  </tr>
</table>  
<b>dynamic http response</b>
<table style="width:100%">
  <tr>
    <th>Method</th>
    <th>EndPoint</th>
    <th>Request</th>
     <th>Response</th>
  </tr>
  <tr>
    <td>GET</td>
    <td>/v1/test?name=${Q&name}</td>
    <td><code></code></td>
    <td><code>{ "name": "my name is ${Q&name}"}</code></td>
  </tr>
    <tr>
    <td>POST</td>
    <td>/v1/test/dynamic</td>
    <td><code>{ "h1": "h2","h3": [{"h4": "${B{[0{h4}"},{"h6": "${B{[1{h6}" }}</code></td>
    <td><code>{"hello": "${B{[0{h4}","hello2": "${B{[1{h6}"}</code></td>
  </tr>
   <tr>
    <td>POST</td>
    <td>/v1/test/dynamic?h1=${Q&h1}&h2=${Q&h2}</td>
    <td><code>{ "h1": "h2","h3": [{"h4": "${B{[0{h4}"},{"h6": "${B{[1{h6}" }}</code></td>
    <td><code>{"hello": "${B{[0{h4}","hello2": "${B{[1{h6}","h1query": "${Q&h1}",
  "h2query": "${Q&h2}"}</code></td>
  </tr>
  </table>
  <p>Here Q for query parameter and B for Body parameter</p>
