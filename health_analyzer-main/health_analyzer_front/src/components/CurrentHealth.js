import '../App.css'
import React from 'react'

export default function CurrentHealth(props) {
    let color = 'health-good'
    if (props.health.cpu >= 40 || props.health.gpu >= 40 || props.health.ram >= 40) color = 'health-normal'
    if (props.health.cpu >= 90 || props.health.gpu >= 90 || props.health.ram >= 90) color = 'health-bad'

    function OnClick() {
        window.history.pushState({}, undefined, '/' + props.health.name)
        window.location.reload()
    }

    return (
        <div className={'current-health-wrapper ' + color} onClick={() => {OnClick()}}>
            <div className='health-title'>{props.health.name}</div>
            <div className='health-value'>cpu: {props.health.cpu}%</div>
            <div className='health-value'>gpu: {props.health.gpu}%</div>
            <div className='health-value'>ram: {props.health.ram}%</div>
        </div>
    );
}
