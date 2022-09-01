import '../App.css'
import React from 'react'
import CurrentHealth from '../components/CurrentHealth'

export default function CurrentHealthPage() {
    const [firstLoad, setFirstLoad] = React.useState(true)
    const [clients, setClients] = React.useState([])

    if (firstLoad) {
        setFirstLoad(false)
        Update()
        setInterval(() => {Update()}, 5000);
    }

    async function Update() {
        let response = await fetch('http://localhost:8080/health/get_clients', {method: 'GET'})
        let responseJson = await response.json()
        setClients(responseJson)
    }

    return (
        <div className='App'>

            <div className='CurrentHealthPage'>
                <div className='page-title'>Все узлы</div>
                <div className='current-health-list'>
                    {clients.map(function(health) {
                        return (<CurrentHealth health={health} />)
                    })}
                </div>
            </div>

            {/*<form name="forgotpassword" action="pages/forgotpassword" method="POST">*/}
            {/*    <ul>*/}
            {/*        <li><label>User:</label> <input type='text' name='j_username'/></li>*/}
            {/*        <li><label>&nbsp;</label> <input type="submit" value="OK" className="btn"/></li>*/}
            {/*    </ul>*/}
            {/*</form>*/}
        </div>


    )
}
